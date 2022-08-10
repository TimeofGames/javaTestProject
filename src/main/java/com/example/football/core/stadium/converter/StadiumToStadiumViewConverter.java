package com.example.football.core.stadium.converter;

import com.example.football.core.stadium.Stadium;
import com.example.football.core.stadium.web.StadiumView;
import com.example.football.core.tournament.converter.TournamentToTournamentViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StadiumToStadiumViewConverter implements Converter<Stadium, StadiumView> {
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;

    public StadiumToStadiumViewConverter(TournamentToTournamentViewConverter tournamentToTournamentViewConverter) {
        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
    }

    @Override
    public StadiumView convert(Stadium stadium) {
        StadiumView view = new StadiumView();
        view.setId(stadium.getId());
        view.setName(stadium.getName());
        view.setCapacity(stadium.getCapacity());
        view.setAccreditationList(stadium.getAccreditationList()
                .stream()
                .map(tournamentToTournamentViewConverter::convert)
                .collect(Collectors.toList()));
        return view;
    }
}
