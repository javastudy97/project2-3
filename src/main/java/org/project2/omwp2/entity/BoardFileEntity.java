package org.project2.omwp2.entity;

<<<<<<< HEAD
public class BoardFileEntity {
=======

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "boardfile")
public class BoardFileEntity {

    //파일 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bfile_id")
    private Long bfileId;

    //파일 원래이름
    @Column(name = "bfile_old_name", nullable = false)
    private String bfileOldName;
    
    //파일 새로운이름
    @Column(name = "bfile_new_name", nullable = false)
    private String bfileNewName;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "board_id")
//    private BoardEntity boardEntity;

>>>>>>> f62f0b17c49ed4abf17886de8ff27d49387e6f6e
}
