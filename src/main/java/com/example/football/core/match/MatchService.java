package com.example.football.core.match;

import com.example.football.core.match.converter.MatchToMatchViewConverter;
import com.example.football.core.match.web.MatchBaseReq;
import com.example.football.core.match.web.MatchLastReq;
import com.example.football.core.match.web.MatchView;
import com.example.football.core.stadium.Stadium;
import com.example.football.core.stadium.StadiumService;
import com.example.football.core.team.TeamRepo;
import com.example.football.core.tournament.TournamentRepo;
import com.example.football.error.AccreditationException;
import com.example.football.error.EntityNotFoundException;
import com.example.football.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepo matchRepo;
    private final MatchToMatchViewConverter matchToMatchViewConverter;
    private final TeamRepo teamRepo;
    private final TournamentRepo tournamentRepo;
    private final MessageUtil messageUtil;

    private final StadiumService stadiumService;

    public MatchService(MatchRepo matchRepo,
                        MatchToMatchViewConverter matchToMatchViewConverter,
                        TeamRepo teamRepo,
                        TournamentRepo tournamentRepo,
                        MessageUtil messageUtil, StadiumService stadiumService) {
        this.matchRepo = matchRepo;
        this.matchToMatchViewConverter = matchToMatchViewConverter;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
        this.messageUtil = messageUtil;
        this.stadiumService = stadiumService;
    }

    public List<MatchView> findLastMatch(MatchLastReq req) {
        List<Match> matches = matchRepo.getLastMatch(req.getPlayerSurname(), req.getPlayerName());
        List<MatchView> matchViews = new ArrayList<>();
        matches.forEach(match -> matchViews.add(matchToMatchViewConverter.convert(match)));
        return matchViews;
    }

    public Match findMatchOrThrow(Long id) {
        return matchRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("match.NotFound", id)));
    }

    public MatchView getMatch(Long id) {
        Match match = findMatchOrThrow(id);
        return matchToMatchViewConverter.convert(match);
    }

    public Page<MatchView> findAllMatch(Pageable pageable) {
        Page<Match> matches = matchRepo.findAll(pageable);
        List<MatchView> matchViews = new ArrayList<>();
        matches.forEach(match -> {
            MatchView matchView = matchToMatchViewConverter.convert(match);
            matchViews.add(matchView);
        });
        return new PageImpl<>(matchViews, pageable, matches.getTotalElements());
    }

    public MatchView create(MatchBaseReq req) {
        Match match = new Match();
        checkAccreditation(match, req);
        this.prepare(match, req);
        Match matchSave = matchRepo.save(match);
        return matchToMatchViewConverter.convert(matchSave);
    }

    public void delete(Long id) {
        try {
            matchRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("match.NotFound", id));
        }
    }

    public MatchView update(Match match, MatchBaseReq req) {
        checkAccreditation(match, req);

        Match newMatch = this.prepare(match, req);
        Match matchSave = matchRepo.save(newMatch);
        return matchToMatchViewConverter.convert(matchSave);
    }

    private Match prepare(Match match, MatchBaseReq req) {
        match.setMatchDate(req.getMatchDate());
        match.setScoreOwners(req.getScoreOwners());
        match.setScoreGuests(req.getScoreGuests());
        match.setTournament(tournamentRepo.getOne(req.getTournamentId()));
        match.setOwner(teamRepo.getOne(req.getTeamOwnerId()));
        match.setGuest(teamRepo.getOne(req.getTeamGuestId()));
        match.setStadium_id(req.getStadium_id());
        return match;
    }

    private void checkAccreditation(Match match, MatchBaseReq req) {
        Stadium stadium = stadiumService.getStadiumByIdOrThrow(req.getStadium_id());

        if (!stadium.getAccreditationList().contains(match.getTournament())) {
            throw new AccreditationException(messageUtil.getMessage("accreditation.Wrong"));
        }
    }
}
