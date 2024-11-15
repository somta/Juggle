package net.somta.juggle.core.model;

public class Student {
    private String name;
    private Integer age;
    private String birthday;

    private Boolean studyFlag;

    private Father father;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getStudyFlag() {
        return studyFlag;
    }

    public void setStudyFlag(Boolean studyFlag) {
        this.studyFlag = studyFlag;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }
}
