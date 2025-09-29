package model;

public abstract class Person {
    protected String name;
    protected int age;
    protected String address;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.address = "";
    }

    public String getPersonalInfo() {
        return "Name: " + name + ", Age: " + age + ", Address: " + address;
    }

    public String getRole() {
        return "Person";
    }
}
