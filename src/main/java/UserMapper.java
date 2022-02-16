import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {


    public List<User> getAllUserByFirstName(String fname) throws Exception {
        try {
            Connection connection = DBconnector.connection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usertable WHERE fname = ?");
            preparedStatement.setString(1,fname);

            ResultSet rs = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()){
                String name = rs.getString("fname");
                String lname = rs.getString("lname");
                String pw = rs.getString("pw");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                User user = new User(name,lname,pw,phone,address);
                users.add(user);
            }
            return users;

        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Prøv igen" + e.getMessage());
        }
    }
    public User updateUser(String fname, String newFname) throws Exception {
        User user;
        try {
            Connection connection = DBconnector.connection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usertable SET fname = ? WHERE fname = ?");
            preparedStatement.setString(1,newFname);
            preparedStatement.setString(2, fname);

            preparedStatement.executeUpdate();

            user = getDetailsOfASpecificUser(newFname);
            return user;


        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Prøv igen" + e.getMessage());
        }
    }

    public User getDetailsOfASpecificUser(String fname) throws Exception {
        try {
            Connection connection = DBconnector.connection();
            User user = null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usertable WHERE fname = ?");
            preparedStatement.setString(1,fname);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("fname");
                String lname = rs.getString("lname");
                String pw = rs.getString("pw");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                user = new User(name,lname,pw,phone,address);
            }
            return user;

        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Prøv igen" + e.getMessage());
        }
    }

}
