package java.Model.AbstracUser;

public class Person {
    public String UserFirstname  , UserLastName , Email , Password ;
    public int PhoneNumber  ;

    public Person() {

    }
    public Person(String userFirstname, String userLastName, String email, String password, int phoneNumber) {
        UserFirstname = userFirstname;
        UserLastName = userLastName;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
    }



    public String getUserFirstname() {
        return UserFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        UserFirstname = userFirstname;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        UserLastName = userLastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserFirstname='" + UserFirstname + '\'' +
                ", UserLastName='" + UserLastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                '}';
    }
}
