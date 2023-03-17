package org.project2.omwp2.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "payment_tb")
public class PaymentEntity {

    // 결재 첨부서류 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id", unique = true, nullable = false)
    public Long pay_id;

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
