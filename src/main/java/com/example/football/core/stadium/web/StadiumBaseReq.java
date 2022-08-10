package com.example.football.core.stadium.web;

import com.example.football.base.BaseRequest;
import com.example.football.core.tournament.web.TournamentView;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

public class StadiumBaseReq extends BaseRequest {
    @Size(min = 1, max = 32)
    private String name;
    @Min(10)
    private long capacity;

    private List<@Valid Id> accreditationList;


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

    public List<Id> getAccreditationList() {
        return accreditationList;
    }

    public void setAccreditationList(List<Id> accreditationList) {
        this.accreditationList = accreditationList;
    }
}
