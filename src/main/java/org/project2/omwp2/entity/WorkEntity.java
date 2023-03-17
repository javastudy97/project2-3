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
@Table(name = "WorkAttendance")
public class WorkEntity {

    //근태 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_Id")
    private Long attendanceId;

    //출근
    @Column(name = "attendance_Start")
    private LocalDateTime attendanceStart;

    //퇴근
    @Column(name = "attendance_end")
    private LocalDateTime attendanceEnd;

    //지각 및 참석여부 0결석, 1지각, 2참석
    @Column(name = "attendance_Check",nullable = true)
    private Long ScheduleProgress;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;




}
