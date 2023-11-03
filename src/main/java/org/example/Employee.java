package org.example;

public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;


    public Employee() {

    }


    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }
}
