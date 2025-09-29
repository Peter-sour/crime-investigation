package model;

import java.util.ArrayList;
import java.util.List;

public class Detective {
    private String name;
    private String rank;
    private List<Case> assignedCases;

    public Detective(String name, String rank) {
        this.name = name;
        this.rank = rank;
        this.assignedCases = new ArrayList<>();
    }

    public void addCase(Case c) {
        assignedCases.add(c);
    }

    public void solveCase(Case c) {
        c.closeCase();
    }

    public String getInfo() {
        return "Detective: " + name + ", Rank: " + rank + ", Assigned cases: " + assignedCases.size();
    }

    public void promoteRank() {
        this.rank = "Senior " + rank;
    }

    public String getName() {
        return name;
    }
}
