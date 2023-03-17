package org.project2.omwp2.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "teamSchedule")
public class TeamScheduleEntity {

    //스케줄 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Schedule_Id")
    private Long ScheduleId;

    //스케줄 내용
    @Column(name = "Schedule_board")
    private String ScheduleBoard;

    //스케줄 시작일
    @Column(name = "Schedule_start")
    private LocalDateTime ScheduleStart;

    //스케줄 종료일
    @Column(name = "Schedule_end")
    private LocalDateTime ScheduleEnd;

    //스케줄 완료여부
    @Column(name = "Schedule_progress",nullable = true)
    private Long ScheduleProgress;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;



}
