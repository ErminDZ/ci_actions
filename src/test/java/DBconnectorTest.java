import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBconnectorTest {

    UserMapper mapper;

    @BeforeEach
    void setUp() {

        // oprette UserMapper instance
        mapper = new UserMapper();

        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5, "Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() throws SQLException, ClassNotFoundException {
        Connection connection = DBconnector.connection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usertable");
        preparedStatement.executeUpdate();
    }

    @Test
    void getAllUserByFirstNameTest() throws Exception {
        List<User> users = mapper.getAllUserByFirstName("Hans");
        assertEquals(1, users.size());
    }

    @Test
    void getDetailsOfSpecificUserTest() throws Exception{
        User user = mapper.getDetailsOfASpecificUser("Hans");
        assertEquals("Hans", user.getFname());
    }

    @Test
    void updateUserTest() throws Exception{
        User user = mapper.updateUser("Hans", "Steve");
        assertEquals("Steve", user.getFname());
    }


}