package org.project2.omwp2.calendar.repository;

import org.project2.omwp2.entity.TeamScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamScheduleRepository extends JpaRepository<TeamScheduleEntity, Long> {


    @Query(value = "select * from team_schedule where m_id=:id",nativeQuery = true)
    List<TeamScheduleEntity> findAllByMId(@Param("id") Long userId);



}
