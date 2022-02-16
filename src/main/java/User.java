public class User {

    private String fname;
    private String lname;
    private String pw;
    private int phone;
    private String address;

    public User(String fname, String lname, String pw, int phone, String address) {
        this.fname = fname;
        this.lname = lname;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}