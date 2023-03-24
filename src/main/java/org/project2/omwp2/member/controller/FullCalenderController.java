package org.project2.omwp2.member.controller;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.calendar.service.TeamScheduleService;
import org.project2.omwp2.dto.TeamScheduleDto;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@RequiredArgsConstructor
public class FullCalenderController {

    private final TeamScheduleService teamScheduleService;

    //    RestController이므로 @ResoponseBody 불필요
    @GetMapping("/events")
    public List<TeamScheduleDto> eventsCalender(){

        List<TeamScheduleDto> teamScheduleDtoList = teamScheduleService.getEvents();

        return teamScheduleDtoList;

    }

    //    DB에 일정추가
    @PostMapping("/calendar")
    public List<TeamScheduleDto> setCalender(@ModelAttribute TeamScheduleDto teamScheduleDto,
                                             Principal principal) {

        String mEmail = principal.getName();

        teamScheduleService.setCalender(teamScheduleDto, mEmail);

        return teamScheduleService.eventListAll();
    }

    @GetMapping("calendar")
    public List<TeamScheduleDto> getCalender(){
        return teamScheduleService.eventListAll();
    }

}
