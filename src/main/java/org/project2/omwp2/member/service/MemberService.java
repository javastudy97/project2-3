package org.project2.omwp2.member.service;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
}
