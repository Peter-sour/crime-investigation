package model;

import java.util.ArrayList;
import java.util.List;

public class Case {
    private String caseID;
    private String title;
    private String status;
    private List<Person> people;
    public List<Person> getPeople() {
    return people;
}


    public Case(String id, String title) {
        this.caseID = id;
        this.title = title;
        this.status = "Open";
        this.people = new ArrayList<>();
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void closeCase() {
        this.status = "Closed";
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Case ID: ").append(caseID)
          .append(", Title: ").append(title)
          .append(", Status: ").append(status)
          .append("\nPeople involved:\n");
        for (Person p : people) {
            sb.append("- ").append(p.getPersonalInfo()).append("\n");
        }
        return sb.toString();
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}
