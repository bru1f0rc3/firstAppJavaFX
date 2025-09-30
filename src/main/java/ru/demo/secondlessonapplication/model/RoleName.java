package ru.demo.secondlessonapplication.model;

public enum RoleName {
    ADMIN("Администратор"),
    MANAGER("Менеджер"),
    PARTICIPANT("Участник");

    private final String name;

    RoleName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
