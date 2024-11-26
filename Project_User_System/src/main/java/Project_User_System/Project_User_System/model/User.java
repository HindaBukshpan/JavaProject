package Project_User_System.Project_User_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class User {
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private Integer age;
    private String city;
    private String street;
    @JsonProperty("num_of_house")
    private Integer numOfHouse;
    @JsonProperty("joining_date")
    private LocalDate joiningDate;

    public User(int id, String firstName, String lastName, String email, Integer age, String city, String street, Integer numOfHouse, LocalDate joiningDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.joiningDate = LocalDate.now();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumOfHouse() {
        return numOfHouse;
    }

    public void setNumOfHouse(Integer numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
