package org.project2.omwp2.notice.service;


import lombok.RequiredArgsConstructor;
import org.project2.omwp2.dto.NoticeDto;
import org.project2.omwp2.entity.MemberEntity;
import org.project2.omwp2.entity.NoticeEntity;
import org.project2.omwp2.member.repository.MemberRepository;
import org.project2.omwp2.notice.reposistory.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;


    public Page<NoticeDto> optionNoticeIDSearch(Long noticeId, Pageable pageable) {

        Page<NoticeEntity> noticeEntityPage = noticeRepository.findByNoticeId(noticeId,pageable);
        Page<NoticeDto> noticeDtoPage = noticeEntityPage.map(NoticeDto::toNoticeDto);

        return noticeDtoPage;
    }

    public Page<NoticeDto> optionNoticeTitleSearch(String search, Pageable pageable) {

        Page<NoticeEntity> noticeEntityPage = noticeRepository.findByNoticeTitleContaining(search,pageable);
        Page<NoticeDto> noticeDtoPage = noticeEntityPage.map(NoticeDto::toNoticeDto);

        return noticeDtoPage;
    }

    public Page<NoticeDto> optionNoticeContentSearch(String search, Pageable pageable) {

        Page<NoticeEntity> noticeEntityPage = noticeRepository.findByNoticeContentContaining(search,pageable);
        Page<NoticeDto> noticeDtoPage = noticeEntityPage.map(NoticeDto::toNoticeDto);

        return noticeDtoPage;
    }

    public Page<NoticeDto> noticeAll(Pageable pageable) {

        Page<NoticeEntity> noticeEntityPage = noticeRepository.findAll(pageable);
        Page<NoticeDto> noticeDtoPage = noticeEntityPage.map(NoticeDto::toNoticeDto);

        return noticeDtoPage;
    }


    @Transactional
    public void InsertNoticeDo(NoticeDto noticeDto, String mEmail) {

        Optional<MemberEntity> optionalMemberEntity
                = memberRepository.findBymEmail(mEmail);

        MemberEntity memberEntity = optionalMemberEntity.get();

        NoticeEntity noticeEntity = NoticeEntity.toNoticeInsertEntity(noticeDto,memberEntity);
        noticeRepository.save(noticeEntity);

    }
}
