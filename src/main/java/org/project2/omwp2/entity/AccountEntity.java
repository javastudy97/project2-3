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
@Builder
@ToString
@Entity
@Table(name="account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id", unique = true)
    private Long acId;

    // 제목
    @Column(name = "ac_title", nullable = false)
    private String acTitle;

    // 수입 지출 항목
    @Column(name = "ac_item", nullable = false)
    private String acItem;

    // 수입 금액
    @Column(name = "ac_income", nullable = false)
    private int acIncome;

    // 지출 금액
    @Column(name = "ac_expend", nullable = false)
    private int acExpend;

    // 잔여금(이월금)?
    @Column(name = "ac_next_money", nullable = false)
    private int acNextMoney;

    // 게시글 등록일
    @CreationTimestamp
    @Column(name = "ac_create", updatable = false)
    private LocalDateTime acCreate;

    // 게시글 수정일
    @UpdateTimestamp
    @Column(name = "ac_update", insertable = false)
    private LocalDateTime acUpdate;
}
