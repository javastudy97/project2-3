package org.project2.omwp2.entity;

<<<<<<< HEAD
public class BoardEntity {
=======

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "board")
public class BoardEntity {

    //게시판 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    //등록일
    @CreationTimestamp
    @Column(name = "board_createday", updatable = false)
    private LocalDateTime boardCreateday;

    //수정일
    @UpdateTimestamp
    @Column(name = "board_updateday", insertable = false)
    private LocalDateTime boardUpdateday;

    //제목
    @Column(name = "board_title", nullable = false)
    private String boardTitle;

    //내용
    @Column(name = "board_content", nullable = false)
    private String boardContent;

    //조회수
    @Column(name = "board_hit")
    private int boardHit;

    //댓글수
    @Column(name = "board_cmcount")
    private int boardCmcount;

    @Column(nullable = false)
    private int attachFile; // 이미지 유무(1,0)


    // FK=> 사원정보 쓸수 있게 Member:Board -> 1:N 관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "m_id")
//    private MemberEntity memberEntity;

    // @ManyToOne(fetch = FetchType.LAZY) 사용하면 쿼리가 순차적으로 나간다.
    //  @ManyToOne(fetch = FetchType.EAGER) 사용하면 즉시 전체쿼리가 나오기때문에 원하지 않은쿼리가 나갈수도 있음
//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @Builder.Default
//    private List<BoardFileEntity> boardFileEntities = new ArrayList<>();









>>>>>>> f62f0b17c49ed4abf17886de8ff27d49387e6f6e
}
