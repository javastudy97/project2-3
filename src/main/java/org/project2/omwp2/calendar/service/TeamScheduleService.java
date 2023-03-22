package org.project2.omwp2.calendar.service;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.calendar.repository.TeamScheduleRepository;
import org.project2.omwp2.dto.TeamScheduleDto;
import org.project2.omwp2.entity.MemberEntity;
import org.project2.omwp2.entity.TeamScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamScheduleService {

    private final TeamScheduleRepository teamScheduleRepository;


    // 팀 스케줄 추가
    @Transactional
    public void insertTeamSchedule(TeamScheduleDto teamScheduleDto, Long mId) {

        TeamScheduleEntity teamScheduleEntity = TeamScheduleEntity.toTeamScheduleEntity(teamScheduleDto);

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMId(mId);

        teamScheduleEntity.setMemberEntity(memberEntity);

        teamScheduleRepository.save(teamScheduleEntity);

    }


    //개인 일정 조회
    public List<TeamScheduleDto> selectTeamSchedule(Long userId) {

        List<TeamScheduleDto> teamScheduleDtoList = new ArrayList<>();

        List<TeamScheduleEntity> teamScheduleEntityList = teamScheduleRepository.findAllByMId(userId);

        for(TeamScheduleEntity teamScheduleEntity : teamScheduleEntityList){

            teamScheduleDtoList.add(TeamScheduleDto.toTeamScheduleDto(teamScheduleEntity));

        }

        return teamScheduleDtoList;

    }

    @Transactional
    public void changeTeamSchedule(TeamScheduleDto teamScheduleDto1) {

        TeamScheduleEntity teamScheduleEntity = TeamScheduleEntity.toTeamScheduleEntity(teamScheduleDto1);

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMId(teamScheduleDto1.getMId());

        teamScheduleEntity.setMemberEntity(memberEntity);

        teamScheduleRepository.save(teamScheduleEntity);

    }

    public void deleteTeamSchedule(Long id) {

        Optional<TeamScheduleEntity> optionalTeamScheduleEntity = teamScheduleRepository.findById(id);

        TeamScheduleEntity teamScheduleEntity = optionalTeamScheduleEntity.get();

        teamScheduleRepository.delete(teamScheduleEntity);
    }

    public Page<TeamScheduleDto> teamScheduleList(Pageable pageable) {

        Page<TeamScheduleEntity> teamScheduleEntities = teamScheduleRepository.findAll(pageable);

        Page<TeamScheduleDto> teamScheduleDtoPage = teamScheduleEntities.map(TeamScheduleDto::toTeamScheduleDto);

        return teamScheduleDtoPage;
    }

    public TeamScheduleDto TeamScheduleDetail(Long scheduleId) {

        Optional<TeamScheduleEntity> optionalTeamScheduleEntity = teamScheduleRepository.findById(scheduleId);

        TeamScheduleEntity teamScheduleEntity = optionalTeamScheduleEntity.get();

        TeamScheduleDto teamScheduleDto = TeamScheduleDto.toTeamScheduleDto(teamScheduleEntity);

        return teamScheduleDto;


    }
}
