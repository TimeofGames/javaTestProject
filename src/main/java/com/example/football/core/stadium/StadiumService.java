package com.example.football.core.stadium;

import com.example.football.core.stadium.converter.StadiumBaseReqToStadiumUpdater;
import com.example.football.core.stadium.converter.StadiumToStadiumViewConverter;
import com.example.football.core.stadium.web.StadiumBaseReq;
import com.example.football.core.stadium.web.StadiumView;
import com.example.football.error.EntityNotFoundException;
import com.example.football.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class StadiumService {
    private final StadiumRepo stadiumRepo;
    private final StadiumToStadiumViewConverter stadiumToStadiumView;

    private final MessageUtil messageUtil;

    private final StadiumBaseReqToStadiumUpdater stadiumBaseReqToStadiumUpdater;

    public StadiumService(StadiumRepo stadiumRepo, StadiumToStadiumViewConverter stadiumToStadiumView, MessageUtil messageUtil, StadiumBaseReqToStadiumUpdater stadiumBaseReqToStadiumUpdater) {
        this.stadiumRepo = stadiumRepo;
        this.stadiumToStadiumView = stadiumToStadiumView;
        this.messageUtil = messageUtil;
        this.stadiumBaseReqToStadiumUpdater = stadiumBaseReqToStadiumUpdater;
    }

    public StadiumView getStadium(long id) {
        return stadiumToStadiumView.convert(getStadiumByIdOrThrow(id));
    }

    public Stadium getStadiumByIdOrThrow(Long id) {
        return stadiumRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("stadium.NotFound")));
    }

    public Page<StadiumView> getAllStadiums(Pageable pageable) {
        Page<Stadium> stadiums = stadiumRepo.findAll(pageable);
        return new PageImpl<>(stadiums.stream().map(stadiumToStadiumView::convert).collect(Collectors.toList()), pageable, stadiums.getTotalElements());
    }

    public StadiumView createStadium(StadiumBaseReq stadiumBaseReq) {
        Stadium stadium = new Stadium();
        stadium = stadiumBaseReqToStadiumUpdater.convert(stadium, stadiumBaseReq);
        stadiumRepo.save(stadium);
        return stadiumToStadiumView.convert(stadium);
    }

    @Transactional
    public void deleteStadium(long id) {
        try {
            stadiumRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("stadium.NotFound", id));
        }
    }
    @Transactional
    public StadiumView updateStadium(long id, StadiumBaseReq stadiumBaseReq) {
        Stadium stadium = stadiumBaseReqToStadiumUpdater.convert(getStadiumByIdOrThrow(id), stadiumBaseReq);
        stadiumRepo.save(stadium);
        return stadiumToStadiumView.convert(stadium);
    }

}



