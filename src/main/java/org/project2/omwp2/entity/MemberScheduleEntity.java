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
@Table(name = "memberSchedule")
public class MemberScheduleEntity {

    //스케줄 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long ScheduleId;

    //스케줄 내용
    @Column(name = "schedule_content", nullable = false)
    private String ScheduleBoard;

    //스케줄 시작일
    @Column(name = "schedule_start",nullable = false)
    private LocalDateTime ScheduleStart;

    //스케줄 종료일
    @Column(name = "schedule_end",nullable = false)
    private LocalDateTime ScheduleEnd;

    //스케줄 완료여부 - 미완료 : N(기본값), 완료 : Y
    @Column(name = "schedule_done",nullable = true)
    private String scheduleDone;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;



}