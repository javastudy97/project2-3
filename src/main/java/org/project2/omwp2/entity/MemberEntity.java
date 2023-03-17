package org.project2.omwp2.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.project2.omwp2.member.constant.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long mId;

    @Column(name = "m_email",nullable = false)
    private String mEmail;

    @Column(name = "m_pw", nullable = false)
    private String mPw;

    @Column(name = "m_zipcode", nullable = false)
    private String mZipcode;

    @Column(name = "m_addr1", nullable = false)
    private String mAddr1;

    @Column(name = "m_addr2")
    private String mAddr2;

    @Column(name = "m_name", nullable = false)
    private String mName;

    @Column(name = "m_tel", nullable = false)
    private String mTel;

    @Column(name = "m_status")
    private int mStatus;

    @Column(name = "m_intro")
    private String mIntro;

    @CreationTimestamp
    @Column(name = "m_create",updatable = false)
    private LocalDateTime mCreate;

//  관리자 권한 (Y : 관리자, N : 일반회원- 기본값)
    @Enumerated(EnumType.STRING)
    private Role mAdmin;

// 프로필 사진 유무 (1 : o , 0 : x)
    @Column(name = "m_attach")
    private int mAttach;

}
