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
@Table(name = "comment")
public class CommentEntity {

    //댓글 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    //등록일
    @CreationTimestamp
    @Column(name = "comment_createday", updatable = false)
    private LocalDateTime commentCreateday;

    //수정일
    @UpdateTimestamp
    @Column(name = "comment_updateday", insertable = false)
    private LocalDateTime commentUpdateday;

//    //별명
//    @Column(name = "comment_nickname", insertable = false)
//    private String commentNickname;

    //댓글 내용
    @Column(name = "comment_content", nullable = false)
    private String commentContent;


}
