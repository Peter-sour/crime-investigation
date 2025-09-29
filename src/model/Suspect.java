package model;

public class Suspect extends Person {
    private String alibi;
    private boolean isArrested;

    public Suspect(String name, int age, String alibi) {
        super(name, age);
        this.alibi = alibi;
        this.isArrested = false;
    }

    public void arrest() {
        isArrested = true;
    }

    public void updateAlibi(String newAlibi) {
        this.alibi = newAlibi;
    }

    @Override
    public String getRole() {
        return "Suspect";
    }

    @Override
    public String getPersonalInfo() {
        return super.getPersonalInfo() + ", Alibi: " + alibi + ", Arrested: " + isArrested;
    }
}
