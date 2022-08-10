package com.example.football.core.stadium.web;

import com.example.football.core.stadium.StadiumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stadium")
public class StadiumController {
    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public StadiumView getStadium(@PathVariable long id){
        return stadiumService.getStadium(id);
    }

    @GetMapping
    @ResponseBody
    public Page<StadiumView> getAllStadiums(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return stadiumService.getAllStadiums(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StadiumView create(@RequestBody @Valid StadiumBaseReq req){
        return stadiumService.createStadium(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStadium(@PathVariable Long id){
        stadiumService.deleteStadium(id);
    }

    @PutMapping("/{id}")
    public StadiumView updateStadium(@PathVariable long id, @RequestBody @Valid StadiumBaseReq req){
        return stadiumService.updateStadium(id,req);
    }
}