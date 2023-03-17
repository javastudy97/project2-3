package org.project2.omwp2.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "notice")
public class NoticeEntity {

    // 공지사항 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    //등록일
    @CreationTimestamp
    @Column(name = "notice_createday", updatable = false)
    private LocalDateTime noticeCreateday;

    //수정일
    @UpdateTimestamp
    @Column(name = "notice_updateday", insertable = false)
    private LocalDateTime noticeUpdateday;

    //제목
    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;

    @Column(name = "notice_content", nullable = false)
    private String noticeContent;

    @Column(name = "notice_hit")
    private String noticeHit;

}
