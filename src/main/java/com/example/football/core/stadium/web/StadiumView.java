package com.example.football.core.stadium.web;

import com.example.football.core.tournament.web.TournamentView;

import java.util.List;

public class StadiumView {
    private long id;

    private String name;

    private long capacity;

    private List<TournamentView> accreditationList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public List<TournamentView> getAccreditationList() {
        return accreditationList;
    }

    public void setAccreditationList(List<TournamentView> accreditationList) {
        this.accreditationList = accreditationList;
    }
}
