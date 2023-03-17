package org.project2.omwp2.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    //등록일
    @CreationTimestamp
    @Column(name = "notice_create_day", updatable = false)
    private LocalDateTime noticeCreateDay;

    //수정일
    @UpdateTimestamp
    @Column(name = "notice_update_day", insertable = false)
    private LocalDateTime noticeUpdateDay;

    //공지사항 제목
    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;

    //공지사항 내용
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;

    //공지사항 조회 수
    @Column(name = "notice_hit")
    private int noticeHit;

}
