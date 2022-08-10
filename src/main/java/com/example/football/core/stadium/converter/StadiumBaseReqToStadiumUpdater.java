package com.example.football.core.stadium.converter;

import com.example.football.base.BaseRequest;
import com.example.football.core.stadium.Stadium;
import com.example.football.core.stadium.web.StadiumBaseReq;
import com.example.football.core.tournament.TournamentRepo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class StadiumBaseReqToStadiumUpdater {
    private final TournamentRepo tournamentRepo;

    public StadiumBaseReqToStadiumUpdater(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public Stadium convert(Stadium stadium,StadiumBaseReq stadiumBaseReq){
        stadium.setName(stadiumBaseReq.getName());
        stadium.setCapacity(stadiumBaseReq.getCapacity());
        stadium.setAccreditationList(tournamentRepo.findAllById(stadiumBaseReq
                .getAccreditationList()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toList())));
        return stadium;
    }
}
