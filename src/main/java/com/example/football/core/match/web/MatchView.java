package com.example.football.core.match.web;


import com.example.football.core.team.web.TeamView;
import com.example.football.core.tournament.web.TournamentView;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MatchView {

    private long id;

    private TeamView teamOwner;

    private TeamView teamGuest;

    private int scoreOwners;

    private int scoreGuests;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date matchDate;

    private TournamentView tournament;

    private long stadium_id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeamView getTeamOwner() {
        return teamOwner;
    }

    public void setTeamOwner(TeamView teamOwner) {
        this.teamOwner = teamOwner;
    }

    public TeamView getTeamGuest() {
        return teamGuest;
    }

    public void setTeamGuest(TeamView teamGuest) {
        this.teamGuest = teamGuest;
    }

    public int getScoreOwners() {
        return scoreOwners;
    }

    public void setScoreOwners(int scoreOwners) {
        this.scoreOwners = scoreOwners;
    }

    public int getScoreGuests() {
        return scoreGuests;
    }

    public void setScoreGuests(int scoreGuests) {
        this.scoreGuests = scoreGuests;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public TournamentView getTournament() {
        return tournament;
    }

    public void setTournament(TournamentView tournament) {
        this.tournament = tournament;
    }

    public long getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(long stadium_id) {
        this.stadium_id = stadium_id;
    }
}
