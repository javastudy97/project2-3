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
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @CreationTimestamp
    @Column(name = "board_create_day", updatable = false)
    private LocalDateTime boardCreateDay;

    @UpdateTimestamp
    @Column(name = "board_update_day", insertable = false)
    private LocalDateTime boardUpdateDay;

    @Column(name = "board_title",nullable = false)
    private String boardTitle;

    @Column(name = "board_content", nullable = false)
    private String boardContent;

    @Column(name = "board_hit")
    private int boardHit;

    @Column(name = "board_cmcount")
    private int boardCmcount;

    //파일 유무
    @Column(nullable = false)
    private int AttachFile;

}
