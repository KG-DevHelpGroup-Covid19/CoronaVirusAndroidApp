package kg.koronastaff.staffapp.models;

import java.util.ArrayList;
import java.util.HashMap;

public class TestResults {
    String name;
    String surname;
    String city;
    String region;
    String area;
    int age;
    HashMap<String, Boolean> answers;
    String gender;

    public TestResults(String name, String surname,
                       String city, String region,
                       String area, int age, HashMap<String,
            Boolean> answers, String gender) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.region = region;
        this.area = area;
        this.age = age;
        this.answers = answers;
        this.gender = gender;
    }

    public TestResults() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
