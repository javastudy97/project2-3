package org.project2.omwp2.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name= "document")
public class DocumentEntity {

    // 문서 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //문서 추가시 자동 1씩 증가
    @Column(name = "do_id", unique = true)
    private Long do_id;

    // 구분 (expend : 지출결의서, draft : 기안서)
    @Column(name = "do_division")
    private String do_division;

    // 제목
    @Column(name = "do_title", nullable = false)
    private String do_title;

    // 내용
    @Column(name = "do_content", nullable = false, length = 500)
    private String do_content;

    // 상신일(등록일) (등록시 자동생성)
    @Column(name = "do_createTime", updatable = false)
    private LocalDateTime do_createTime;

    // 수정일 (수정시 자동생성)
    @Column(name = "do_updateTime", insertable = false)
    private LocalDateTime do_updateTime;

    // 결재상태 (0 : 승인대기(기본값, 수정가능), 1 : 승인(수정불가), 2 : 반려(수정불가) )
    @Column(name = "do_state")
    private int do_state;

    // 반려사유 (반려 처리시 입력)
    @Column(name = "do_reason")
    private String do_reason;

    // 기안자 ID
    @Column(name = "do_drafter", nullable = false)
    private Long do_drafter;

    // 결재자 ID
    @Column(name = "do_approver", nullable = false)
    private Long do_approver;

    // 첨부서류 ID
    private Long pay_id;

    // 결재서류 1:N 파일
    // mappedBy = "documentEntity"
    // 연관관계의 주인(외래키설정 테이블)
    @OneToMany(mappedBy = "documentEntity",
                // 결재서류 삭제 시 파일 목록도 삭제
                cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PaymentEntity> paymentEntities = new ArrayList<>();

    // 파일 유무(0,1)
    @Column(nullable = false)
    private int pay_attach;


}
