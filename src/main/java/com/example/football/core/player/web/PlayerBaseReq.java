package com.example.football.core.player.web;

import com.example.football.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PlayerBaseReq extends BaseRequest {

    @NotEmpty
    private String surname;

    @NotEmpty
    private String name;

    private int height;

    private int weight;

    private int age;

    private int role;

    @NotEmpty
    private List<@Valid Id> teams;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Id> getTeams() {
        return teams;
    }

    public void setTeams(List<Id> teams) {
        this.teams = teams;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
