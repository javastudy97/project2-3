package org.project2.omwp2.dto;

import lombok.*;
import org.project2.omwp2.entity.NoticeEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NoticeDto {

    private Long noticeId;
    private LocalDateTime noticeCreate;
    private LocalDateTime noticeUpdate;
    private String noticeTitle;
    private String noticeContent;
    private int noticeHit;

    private String mEmail;

    public static NoticeDto toNoticeDto(NoticeEntity noticeEntity) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNoticeId(noticeEntity.getNoticeId());
        noticeDto.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDto.setNoticeContent(noticeEntity.getNoticeContent());
        noticeDto.setNoticeCreate(noticeEntity.getNoticeCreate());
        noticeDto.setNoticeUpdate(noticeEntity.getNoticeUpdate());
        noticeDto.setNoticeHit(noticeEntity.getNoticeHit());
        noticeDto.setMEmail(noticeEntity.getMemberEntity().getMEmail());

        return noticeDto;
    }
}
