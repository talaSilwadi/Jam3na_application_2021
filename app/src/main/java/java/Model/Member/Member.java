package java.Model.Member;

import java.Model.AbstracUser.Person;

public class Member extends Person {

    public double Age , Weight , height , Rate ;
    public char BloodType ;

    public Member(){

    }
    public Member(double age, double weight, double height, double rate, char bloodType) {
        super();
        Age = age;
        Weight = weight;
        this.height = height;
        Rate = rate;
        BloodType = bloodType;
    }

    public double getAge() {
        return Age;
    }

    public void setAge(double age) {
        Age = age;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public char getBloodType() {
        return BloodType;
    }

    public void setBloodType(char bloodType) {
        BloodType = bloodType;
    }
}
