package net.somta.juggle.core.model;

/**
 * Student's father
 */
public class Father {
    private String fatherName;

    private Integer fatherAge;

    public Father(String fatherName, Integer fatherAge) {
        this.fatherName = fatherName;
        this.fatherAge = fatherAge;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getFatherAge() {
        return fatherAge;
    }

    public void setFatherAge(Integer fatherAge) {
        this.fatherAge = fatherAge;
    }
}
