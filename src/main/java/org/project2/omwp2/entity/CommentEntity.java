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
@Table(name = "comment")
public class CommentEntity {

    //댓글 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    //등록일
    @CreationTimestamp
    @Column(name = "comment_create_day", updatable = false)
    private LocalDateTime commentCreateDay;

    //수정일
    @UpdateTimestamp
    @Column(name = "comment_update_day", insertable = false)
    private LocalDateTime commentUpdateDay;

    //내용
    @Column(name = "comment_content", nullable = false)
    private String commentContent;


}
