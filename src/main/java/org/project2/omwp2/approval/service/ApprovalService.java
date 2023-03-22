package org.project2.omwp2.approval.service;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.approval.repository.ApprovalRepository;
import org.project2.omwp2.document.repository.DocumentRepository;
import org.project2.omwp2.dto.ApprovalDto;
import org.project2.omwp2.entity.ApprovalEntity;
import org.project2.omwp2.entity.DocumentEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    // 결재문서 작성시
    private final ApprovalRepository approvalRepository;

    // 결재문서 파일 업로드시
    private final DocumentRepository documentRepository;

    // 결재문서 작성
    @Transactional
    public void insertApproval(ApprovalDto approvalDto) throws IOException{

        // 파일 없을 때
        if (approvalDto.getAppContainer().isEmpty()){

            ApprovalEntity approvalEntity = ApprovalEntity.toNoApprovalEntity(approvalDto);
            approvalRepository.save(approvalEntity);
        }
        // 파일 있을 때
        else {

            MultipartFile multipartFile = approvalDto.getAppContainer();
            // 원본 파일 이름
            String originalFilename = multipartFile.getOriginalFilename();
            // 랜덤 파일 이름
            UUID uuid = UUID.randomUUID();

            //새로운 파일 이름
            String newFilename = uuid + "_"+originalFilename;
//            String newFilename = originalFilename;
            // 파일 저장 위치
            String filePath = "C:/saveFiles/" +newFilename;

            // 파일 경로 탐색
            multipartFile.transferTo(new File(filePath));

            ApprovalEntity approvalEntity = ApprovalEntity.toYesApprovalEntity(approvalDto);
            Long appId = approvalRepository.save(approvalEntity).getAppId();

            Optional<ApprovalEntity> approvalEntity1 = approvalRepository.findById(appId);
            ApprovalEntity approvalEntity2 = approvalEntity1.get();

            DocumentEntity documentEntity = DocumentEntity.toDocumentEntity(approvalEntity2,originalFilename, newFilename);
            documentRepository.save(documentEntity); // 파일 저장

            System.out.println("확인");
        }
    }

    // 결재문서 목록
    public List<ApprovalDto> approvalList(){

        List<ApprovalEntity> approvalEntityList = approvalRepository.findAll();
        List<ApprovalDto> approvalDtoList = new ArrayList<>();

        for (ApprovalEntity approvalEntity : approvalEntityList){
            approvalDtoList.add(ApprovalDto.toApprovalDto(approvalEntity));
        }
        return approvalDtoList;
    }

    // 결재문서 상세페이지
    public ApprovalDto findByApproval(Long id){

        Optional<ApprovalEntity> optionalApprovalEntity = approvalRepository.findById(id);

        if(optionalApprovalEntity.isPresent()) {
            return ApprovalDto.toApprovalDto(optionalApprovalEntity.get());
        }else {
            return null;
        }
    }
}