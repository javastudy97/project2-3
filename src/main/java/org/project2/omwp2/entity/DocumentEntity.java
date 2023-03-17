package org.project2.omwp2.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "document")
public class DocumentEntity {

    // 결재 첨부서류 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id", unique = true, nullable = false)
    public Long appId;

    // 원본파일명
    @Column(nullable = false)
    public String pay_origin;

    // 저장파일명
    @Column(nullable = false)
    public String pay_save;

    // 결재문서 1:N 파일
    @ManyToOne
    @JoinColumn(name = "do_id")
    private DocumentEntity documentEntity;

}
