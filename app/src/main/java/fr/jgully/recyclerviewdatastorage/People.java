package fr.jgully.recyclerviewdatastorage;

public class People {

    public enum Sex {
        MALE,
        FEMALE
    }

    private String name;
    private int age;
    private Sex sex;
    private boolean loveAndroid;


    public People(String name) {
        this.name = name;
    }

    public People(String name, int age, Sex sex, boolean loveAndroid) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.loveAndroid = loveAndroid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean isLoveAndroid() {
        return loveAndroid;
    }

    public void setLoveAndroid(boolean loveAndroid) {
        this.loveAndroid = loveAndroid;
    }
}
