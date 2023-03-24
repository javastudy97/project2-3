package org.project2.omwp2.approval.controller;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.approval.service.ApprovalService;
import org.project2.omwp2.document.service.DocumentService;
import org.project2.omwp2.dto.ApprovalDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/approval")
public class ApprovalController {
    
    private final ApprovalService approvalService;
    private final DocumentService documentService;
    
    // 결재문서 작성
    @GetMapping("/write")
    public String writeGet(Model model){
        model.addAttribute("ApprovalDto",new ApprovalDto());
        return "approval/write";
    }

    @PostMapping("/write")
    public String writePost(@RequestParam("appContainer")MultipartFile files,
                            ApprovalDto approvalDto, Principal principal)throws IOException{

//        기안서 작성시 현재 로그인한 회원정보 추가
        approvalService.insertApproval(approvalDto,principal);
        return "redirect:/approval/list";

    }

    // 결재문서 목록
    @GetMapping("/list")
    public String list(Model model){
        List<ApprovalDto> approvalDtoList = approvalService.approvalList();

        model.addAttribute("approvalDtoList",approvalDtoList);

        return "approval/list";
    }

//    결재문서 상세
    @GetMapping("/listDetail/{id}")
    public String listDetail(@PathVariable("id") Long addId, Principal principal, Model model){

        String mEmail = principal.getName();
        ApprovalDto approval = approvalService.findByApproval(addId);

        if (approval !=null) {
            model.addAttribute("approval", approval);
            model.addAttribute("mEmail",mEmail);
            return "approval/listDetail";
        }else{
            return "redirect:/approval/list";
        }
    }
    
    // 결재문서 수정 페이지
    @GetMapping("/listUpdate/{id}")
    public String listUpdateGet(@PathVariable("id") Long appId, Model model){

        ApprovalDto approval = approvalService.findByApproval(appId);
        model.addAttribute("approval",approval);
        return "approval/listUpdate";
    }

    // 결재문서 수정 실행
    @PostMapping("/listUpdate")
    public String listUpdatePost(@RequestParam(value = "appContainer") MultipartFile files, @ModelAttribute ApprovalDto approvalDto) throws IOException {
        approvalService.updateApproval(approvalDto);

        return "redirect:/approval/list";
    }

    // 결재문서 삭제
    @GetMapping("/listDelete/{id}")
    public String listDelete(@PathVariable(value = "id") Long appId){
        approvalService.deleteApproval(appId);
        return "redirect:/approval/list";
    }

//    결재문서 승인
    @GetMapping("/approve/{appId}")
    public String approveDo(@PathVariable(value = "appId") Long appId, Model model) {

        ApprovalDto approvalDto = approvalService.approveDo(appId);

        model.addAttribute("approval",approvalDto);

        return "redirect:/approval/listDetail/"+appId;

    }


//    결재문서 반려
    @GetMapping("/reject/{appId}")
    public String rejectDo(@PathVariable(value = "appId") Long appId,
                           @RequestParam(value = "appReason") String reason,
                           Model model) {

        ApprovalDto approvalDto = approvalService.rejectDo(appId, reason);

        model.addAttribute("approval", approvalDto);

        return "redirect:/approval/listDetail/"+appId;
    }
}
