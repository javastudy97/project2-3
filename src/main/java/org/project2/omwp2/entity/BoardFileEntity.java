package org.project2.omwp2.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
}
