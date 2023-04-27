<p align="center">
  <img src="https://user-images.githubusercontent.com/116870617/231791531-9e7ee801-a462-4b7a-977d-1e56b195e28b.png">
</p>

# KICKOFF (킥오프)
## 프로젝트 기획
- 단순 스포츠 동아리의 효율적인 관리를 그룹웨어 형태로 개발하는 것을 목표
- 스포츠 종목은 SBS 여자 축구예능 '골때리는 그녀들"를 통해서 남자뿐만 아니라 여자들 한테도 인기가 증가되고있는 스포츠 중 하나인 축구를 컨셉
- 다른 사회 동아리, 교내 동아리, 각종 소모임 등에도 활용 가능
- OPEN API 연동해서 날씨, 영화, 버스정보 조회 구현 추가 

### 프로젝트에 사용된 기술사항
- Language => JAVA 11, HTML4, CSS3 , javascript
- IDEA => Intelij
- DB => MySql
- FramWork => Spring boot 2.7.9 or 2.7.10
- Template Engine => Thymeleaf
- ORM => JPA

### 팀 구성 및 역할 
#### 팀장 : 김**
- DB 설계
- 프로젝트 일정 관리 및 발표준비
- 메인 페이지, 관리자 페이지 구현
- 날씨정보 조회 구현(OpenWeather Api 연동)

#### 팀원 : 김**
- 회계내역 구현, Naver API 연동
- 축구 관련 영상 구현(YouTube APi

#### 팀원 : 장**
- 결재관리, 조직관리, 챗봇 구현

#### 팀원 : 이**
- 근태관리, 일정관리 구현
- 영화정보 조회 구현(영화진흥위원회 Api 연동)


### 팀원 : 김홍록
- 공통요소 및 레이아웃 구현
- 커뮤니티 게시판, 공지사항 구현
- 댓글 구현
- 버스노선정보 조회 구현(공공데이터포털에 있는 Api 연동)


<details>
<summary>공통요소 및 레이아웃 구현</summary>

====공통요소 및 레이아웃====
![image](https://user-images.githubusercontent.com/116870683/234750290-3ecec5a7-46a4-48d0-b375-3a6085ff8c4c.png)<br>

====header====
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  <link rel="stylesheet" href="/static/css/common/adminHeader.css">
<div class="header" th:fragment="adminHeaderFragment">
  
  <div style="width: 300px;"></div>
  <div class="logo">
      <a class="logo-img" th:href="@{/}"></a>
  </div>
    <div class="gnb">
      <ul>
        <li><a th:href="@{/logout}" style="color: red;" >Logout</a></li>
      </ul>
    </div>
</div>
</html>
```

====left Menu====

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<link rel="stylesheet" href="/static/css/common/leftMenu.css">
<link rel="stylesheet" th:href="@{/static/css/common/chat-bot.css}">

<div class="left-menu" th:fragment="leftMenuFragment">
    <!--챗봇 -->
    <script th:src="@{/js/chat.js}"></script>
    <!-- 날씨 -->
    <th:block th:if="${key} == null">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js" defer></script>
    </th:block>
        <script th:src="@{/js/weather.js}" defer></script>
        <div class="left-con">
            <div class="dash-board">
                <!-- <h1 class="title">대시보드</a></h1> -->
                <div class="weather">
                    <div class="title">
                        <span class="nowtime"></span>
                        <span class="cityName"></span>
                    </div>
                    <div class="desc">
                        <h3 class="icon"></h3>
                        <div class="today">
                            <h3 class="weatherDesc"></h3>
                            <h2 class="nowTemp"> </h2>
                        </div>
                    </div>
                    <div class="temp">
                        <h4 class="maxTemp"></h4>
                        <h4>/ </h4>
                        <h4 class="minTemp"></h4>
                    </div>
                    <div class="search">
                        <select name="city" id="city">
                            <option value="seoul">서울</option>
                            <option value="incheon">인천</option>
                            <option value="chuncheon">춘천</option>
                            <option value="daejeon">대전</option>
                            <option value="gwangju">광주</option>
                            <option value="busan">부산</option>
                            <option value="jeju">제주</option>
                        </select>
                        <input type="button" value="날씨조회" onclick="searchCity()">
                    </div>
                </div>
                <div class="menu">
                    <ul>
                        <li>
                            <a href="#">회원정보</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/member/detail}">회원상세</a>
                                </li>
                                <li>
                                    <a th:href="@{/member/update}">회원수정</a>
                                </li>
                                <li>
                                    <a th:href="@{/member/myBoardList}">작성글 관리</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">일정관리</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/attend/attend}">근태 관리</a>
                                </li>
                                <li>
                                    <a th:href="@{/memberSchedule/memberSchedule}">개인일정 관리</a>
                                </li>
                                <li sec:authorize="hasRole('ADMIN')">
                                    <a th:href="@{/teamSchedule/teamSchedule}">공식일정 관리</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">결재관리</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/approval/write}">결재서류 작성</a>
                                </li>
                                <li>
                                    <a th:href="@{/approval/list}">결재서류 리스트</a>
                                </li>
                                <li>
                                    <!-- <a th:href="@{/approval/listUpdate}">결재서류 수정</a>-->
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">조직관리</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/member/management}">팀조회</a>
                                </li>
                                <li>
                                    <a th:href="@{/member/managementPlace}">팀배치</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">회계관리</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/account/list}">수입지출 내역</a>
                                </li>
                            </ul>
                        </li>

                        <li>
                            <a href="#">동호회게시판</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/board/boardInsert}">게시글추가</a>
                                </li>
                                <li>
                                    <a th:href="@{/board/boardList}">게시글조회</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li>
                            <a href="">공지사항</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/notice/noticeInsert}">공지사항추가</a>
                                </li>
                                <li>
                                    <a th:href="@{/notice/noticePage}">공지사항조회</a>
                                </li>
                            </ul>
                        </li>

                        <li>
                            <a href="">NAVER API</a>
                            <ul class="sub">
                                <li>
                                    <a href="https://kickoff.ncpworkplace.com/v/home/">Naver login</a>
                                </li>
                                <li>
                                    <form action="https://auth.worksmobile.com/oauth2/v2.0/authorize" method="get">
                                        <input type="hidden" name="client_id" value="H8paKKaUOVv57BJBdBm9">
                                        <input type="hidden" name="redirect_uri" value="http://localhost:8099/naver/auth2">
                                        <input type="hidden" name="scope" value="directory,directory.read,orgunit,orgunit.read">
                                        <input type="hidden" name="response_type" value="code">
                                        <input type="hidden" name="state" value="test">
                                        <button>조직인증</button>
                                    </form>
                                </li>
                                <li>
                                    <form id="mail" onsubmit="window.open('https://mail.worksmobile.com/write/popup', '_blank', 'width=600, height=400,resizeable,scrollbars');" >
					                    <button>메일연동</button>
				                    </form>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="">Open API</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/movie/todaymovie}">영화 API</a>
                                </li>
                                <li>
                                    <a th:href="@{/bus/index}">버스 API</a>
                                </li>
                            </ul>
                        </li>
                        <li sec:authorize="hasRole('ADMIN')">
                            <a href="#">관리자 메뉴</a>
                            <ul class="sub">
                                <li>
                                    <a th:href="@{/member/memberList}">회원 관리</a>
                                </li>
                                <li>
                                    <a th:href="@{/board/adminNoticeList}">공지사항 관리</a>
                                </li>
                                <li>
                                    <a th:href="@{/board/adminBoardList}">게시판 관리</a>
                                </li>
                            </ul>
                        </li>
                        <!-- ChatBot -->
                        <div class="bot-con">
                            <div class="chat-bot">
                                <div class="wrap">
                                    <img th:src="@{/img/icon/chatbot.png}" id="btn-chat-open" onclick="openChat()">
                                    <th:block th:insert="~{chatbot/chat-bot::#chat-disp}" />
                                </div>
                            </div>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</html>
```
</details>

<details>
<summary>커뮤니티 게시판, 공지사항 구현</summary>


====커뮤니티 게시판====
![image](https://user-images.githubusercontent.com/116870683/234750053-6de369ff-df38-478a-a143-bc47f1b69191.png)<br>

====공지사항====
![image](https://user-images.githubusercontent.com/116870683/234750180-f28da932-98f5-46cb-aa52-2162320a651c.png)<br>

====상세페이지====

![image](https://user-images.githubusercontent.com/116870683/234751533-344ab178-9c52-47b0-862f-d1ba73d1f42b.png)<br>

![image](https://user-images.githubusercontent.com/116870683/234752332-8c8469d4-6d0c-4ae3-8a81-713737c52f72.png)<br>


====Controller====
```
package org.project2.omwp2.board.controller;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.board.service.BoardService;
import org.project2.omwp2.comment.service.CommentService;
import org.project2.omwp2.dto.BoardDto;
import org.project2.omwp2.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    
    //게시판 조회(검색,페이징)
    @GetMapping("/boardList")
    public String boardPage(@PageableDefault(page = 0, size = 5, sort = "boardId",
            direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(value = "type", required = false) String type,
                            @RequestParam(value = "search", required = false) String search,
                            Model model) {

//        boardDto.setBoardCmcount(boardService.upcount(boardDto.getBoardId()));


        Page<BoardDto> boardList = null;

        Long boardId = 0L;


        if (type != null && search != null) {
            if (type.equals("boardId")) {
                boardId = Long.parseLong(search);
                boardList = boardService.optionboardIdSearchPaging(boardId, pageable);


            } else if (type.equals("boardTitle")) {
                boardList = boardService.optionboardTitleSearchPaging(search, pageable);

            } else if (type.equals("boardContent")) {
                boardList = boardService.optionboardContentSearchPaging(search, pageable);

            }

        } else {
            boardList = boardService.BoardAllPagingList(pageable);
        }


        int bockNum = 100;
        int nowPage = boardList.getNumber() + 1;
        int startPage = Math.max(1, boardList.getNumber() - bockNum);
        int endPage = boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
//        model.addAttribute("comment",comment);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/boardPage";

    }
    
    //게시글 추가
    @GetMapping("/boardInsert")
    public String boardInsert(Model model) {

        model.addAttribute("boardDto", new BoardDto());
        return "board/BoardInsert";

    }

    @PostMapping("/boardInsert")
    public String boardInsert(@RequestParam("boardFile") MultipartFile files,
                              @Valid BoardDto boardDto, BindingResult bindingResult,
                              Principal principal) throws IOException {

        if(bindingResult.hasErrors()){
            return "board/BoardInsert";
        }

        String mEmail = principal.getName();
        boardService.insertBoard(boardDto, mEmail);
        return "redirect:/board/boardList";
    }
    
    //게시글 상세
    @GetMapping("/boardDetail/{boardId}/{cm}")
    public String boarddetail(@PathVariable Long boardId,
                              @PathVariable int cm,
                              Model model) {

        if (cm == 0) {
            boardService.upHit(boardId);
        }

        BoardDto board = boardService.findByBoard(boardId);

        if (board != null) {

            model.addAttribute("board", board);


            List<CommentDto> commentDtoList = commentService.commentDtoListDo(boardId);
            model.addAttribute("commentDtoList", commentDtoList);

//            boardService.upHit(boardId);
            System.out.println(boardId + " << boardId");

//            model.addAttribute("cmcount",cmcount);
            System.out.println("???????????");

            return "board/BoardDetail";
        } else {
            return "redirect:/board/boardInsert";
        }
    }

    //게시글 수정
    @GetMapping("/boardUpdate/{id}")
    public String boardUpdate(@PathVariable("id") Long boardId, Model model) {

        BoardDto boardDto = boardService.findByBoard(boardId);
        model.addAttribute("board", boardDto);

        return "board/BoardUpdate";

    }
    
    
    @PostMapping("/boardUpdate")
    public String boardUpdateDo(@RequestParam(value = "bfileNewName", required = false) String bfileNewName,
                                @ModelAttribute BoardDto boardDto, Principal principal) {

        String mEmail = principal.getName();
        System.out.println("boardContent "+boardDto.getBoardContent());

        boardService.boardUpdateDo(boardDto, mEmail);

        return "redirect:/board/boardList";

    }
    
    //게시글 삭제
    @GetMapping("/boardDelete/{id}")
    public String boardDelete(@PathVariable(value = "id") Long productId) {
        boardService.boardDeleteDo(productId);

        return "redirect:/board/boardList";
    }


    @GetMapping("/boardSearch")
    public String boardSearch(@RequestParam(value = "type", required = false) String type,
                              @RequestParam(value = "search", required = false) String search,
                              RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("type", type);
        redirectAttributes.addAttribute("search", search);

        return "redirect:/board/boardList";
    }

}

  
```

=== Service ===

```
package org.project2.omwp2.board.service;

import lombok.RequiredArgsConstructor;
import org.project2.omwp2.board.reposistory.BoardReposistory;
import org.project2.omwp2.boardFile.reposistory.BoardFileReposistory;
import org.project2.omwp2.dto.BoardDto;
import org.project2.omwp2.entity.BoardEntity;
import org.project2.omwp2.entity.BoardFileEntity;
import org.project2.omwp2.entity.MemberEntity;
import org.project2.omwp2.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardReposistory boardReposistory;
    private final BoardFileReposistory boardFileReposistory;

    @Transactional
    public void insertBoard(BoardDto boardDto, String mEmail) throws IOException {

        Optional<MemberEntity> optionalMemberEntity1
                =memberRepository.findBymEmail(mEmail);
        MemberEntity memberEntity = optionalMemberEntity1.get();

            if (boardDto.getBoardFile().isEmpty()) {


                BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDto,memberEntity);
                boardReposistory.save(boardEntity);
            } else {

                MultipartFile multipartFile = boardDto.getBoardFile();
                String originalName = multipartFile.getOriginalFilename();
                UUID uuid = UUID.randomUUID(); // 랜덤 이미지 이름

                String newName = uuid + "_" + originalName; //새로운 파일 이름
                String filePath = "C:/saveFiles/" + newName; // 파일 경로 탐색

                multipartFile.transferTo(new File(filePath));

                BoardEntity boardEntity = BoardEntity.toBoardEntityFileInclude(boardDto,memberEntity);
                Long boardId = boardReposistory.save(boardEntity).getBoardId();

                Optional<BoardEntity> boardEntity1 = boardReposistory.findById(boardId);
                BoardEntity boardEntity2 = boardEntity1.get(); //id에 해당하는 상품

                BoardFileEntity boardFileEntity = BoardFileEntity.toFileEntity(boardEntity2, originalName, newName);
                boardFileReposistory.save(boardFileEntity);


            }


    }
    
    //게시판 상세페이지 조회


    public BoardDto findByBoard(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardReposistory.findById(boardId);


        if (optionalBoardEntity.isPresent()){
            return BoardDto.toBoardDto(optionalBoardEntity.get());
        }else {
            return null;
        }
    }
    
    //게시판 검색 (게시글번호 검색)
    public Page<BoardDto> optionboardIdSearchPaging(Long boardId, Pageable pageable) {


        Page<BoardEntity> boardEntityPage = boardReposistory.findByBoardId(boardId,pageable);
        Page<BoardDto> boardDtoPage = boardEntityPage.map(boardEntity -> BoardDto.toBoardDto(boardEntity));


        return  boardDtoPage;
    }


    public Page<BoardDto> optionboardTitleSearchPaging(String search, Pageable pageable) {
        Page<BoardEntity> boardEntityPage = boardReposistory.findByBoardTitleContaining(search, pageable);
        Page<BoardDto> boardDtoPage = boardEntityPage.map(boardEntity -> BoardDto.toBoardDto(boardEntity));

        return boardDtoPage;
    }

    public Page<BoardDto> optionboardContentSearchPaging(String search, Pageable pageable) {
        Page<BoardEntity> boardEntityPage = boardReposistory.findByBoardContentContaining(search, pageable);
        Page<BoardDto> boardDtoPage = boardEntityPage.map(boardEntity -> BoardDto.toBoardDto(boardEntity));

        return boardDtoPage;

    }

    //게시판 전체

    public Page<BoardDto> BoardAllPagingList(Pageable pageable) {

        Page<BoardEntity> boardEntityPage = boardReposistory.findAll(pageable);
        Page<BoardDto> boardDtoPage = boardEntityPage.map(boardEntity -> BoardDto.toBoardDto(boardEntity));

        return boardDtoPage;
    }

    @Transactional
    public void upHit(Long boardId) {
        boardReposistory.upHitGo(boardId);
    }

    @Transactional
    public void UpCommentCount(Long boardId) {
        boardReposistory.upCmcountCount1(boardId);
    }
    
 //게시판 수정
    @Transactional
    public void boardUpdateDo(BoardDto boardDto, String mEmail) {
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findBymEmail(mEmail);

        MemberEntity memberEntity = optionalMemberEntity.get();

        if (boardDto.getBfileOldName().isEmpty()) {
            BoardEntity boardEntity = BoardEntity.toBoardUpdateEntity(boardDto, memberEntity);
            boardReposistory.save(boardEntity);
        }else {
            BoardEntity boardEntity = BoardEntity.toBoardUpdateEntity2(boardDto, memberEntity);
            boardReposistory.save(boardEntity);

        }

    }
//게시판 삭제
    @Transactional
    public void boardDeleteDo(Long productId) {
        boardReposistory.deleteById(productId);
    }

    public Page<BoardDto> myBoardListDo(Long mId, Pageable pageable) {

        Page<BoardEntity> boardEntityPage = boardReposistory.findAllBymId(mId,pageable);
        Page<BoardDto> boardDtoPage = boardEntityPage.map(boardEntity -> BoardDto.toBoardDto(boardEntity));

        return boardDtoPage;
    }


    @Transactional
    public void downCommentCount(Long boardId) {
        boardReposistory.downCmcount(boardId);
    }
}

    
```


</details>

<details>
<summary>댓글구현</summary>

![image](https://user-images.githubusercontent.com/116870683/234752143-127357d4-3d83-4b43-8364-83adc61d0ad3.png)

===Controller===

```
package org.project2.omwp2.comment.controller;


import lombok.RequiredArgsConstructor;
import org.project2.omwp2.board.service.BoardService;
import org.project2.omwp2.comment.service.CommentService;
import org.project2.omwp2.dto.BoardDto;
import org.project2.omwp2.dto.CommentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/commentWrite")
    public String commentWrite(@ModelAttribute CommentDto commentDto,
                               Model model
                               ,Principal principal
                                ){

        String mEmail = principal.getName();

        Long result = commentService.insertCommentDo(commentDto,mEmail);
        System.out.println("mEmail"+commentDto.getMEmail());
        System.out.println("boardId"+commentDto.getBoardId());


//         게시글 번호를 추가 -> 댓글번호가 아니라
//         게시글 번호를 html에서 가져와서
        if (result != 0){ //댓글 수 추가시 댓글수 
            boardService.UpCommentCount(commentDto.getBoardId());
        }

       List<CommentDto> commentDtoList =
               commentService.commentDtoListDo(commentDto.getBoardId());

        model.addAttribute("commentDtoList", commentDtoList);


        return "redirect:/board/boardDetail/"+commentDto.getBoardId()+"/"+1;


    }
    
    //댓글 수정
    @GetMapping("/commentUpdate/{commentId}")
    public String commentUpdate(@PathVariable("commentId") Long commentId, Model model ){

        Long boardId = commentService.findBoardId(commentId);
        BoardDto boardDto = boardService.findByBoard(boardId);

        model.addAttribute("board",boardDto);

        CommentDto commentDto = commentService.findByCommet(commentId);
        model.addAttribute("comment",commentDto);

        return "commentUpdate";
    }
  
    @PostMapping("/commentUpdate")
    public String commentUpdateDo(@ModelAttribute CommentDto commentDto, Principal principal, Model model){

        String mEmail = principal.getName();
        commentService.commentUpdateDo(commentDto,mEmail);

        return "redirect:/board/boardDetail/"+commentDto.getBoardId()+"/"+1;
    }

    // 댓글 삭제
    @GetMapping("/commentDelete/{id}")
    public String commentDelete(@PathVariable(value = "id") Long commentId,
                                Principal principle) {
        String mEmail = principle.getName();

        Long boardId = commentService.findBoardId(commentId);
        int result = commentService.commetDeleteDo(commentId,mEmail);
        if(result == 0){
            return null;
        }

        boardService.downCommentCount(boardId);

        return "redirect:/board/boardDetail/"+boardId+"/"+1;
    }


}
====Service===
package org.project2.omwp2.comment.service;


import lombok.RequiredArgsConstructor;
import org.project2.omwp2.board.reposistory.BoardReposistory;
import org.project2.omwp2.comment.reposistory.CommentRepository;
import org.project2.omwp2.dto.CommentDto;
import org.project2.omwp2.entity.BoardEntity;
import org.project2.omwp2.entity.CommentEntity;
import org.project2.omwp2.entity.MemberEntity;
import org.project2.omwp2.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final MemberRepository memberRepository;
    private final BoardReposistory boardReposistory;
    private final CommentRepository commentRepository;
    
      // 댓글 추가
    @Transactional
    public Long insertCommentDo(CommentDto commentDto,
                                String mEmail
                                ) {
        // member테이블의 이메일이 있는지 확인
        Optional<MemberEntity> optionalMemberEntity
        = memberRepository.findBymEmail(mEmail);

        // product 테이블의 상품번호가 있는지 확인
        Optional<BoardEntity> optionalBoardEntity
                = boardReposistory.findById(commentDto.getBoardId());
        if (optionalBoardEntity.isPresent()
            &&optionalMemberEntity.isPresent()
            ){

            MemberEntity memberEntity = optionalMemberEntity.get();
             BoardEntity boardEntity = optionalBoardEntity.get();

            CommentEntity commentEntity =
                    CommentEntity.toInsertEntity(commentDto, memberEntity, boardEntity);

            return commentRepository.save(commentEntity).getCommentId();

        }else {
            return null;
        }
    }
      
      // 댓글 조회
    public List<CommentDto> commentDtoListDo(Long boardId) {

        BoardEntity boardEntity = boardReposistory.findById(boardId).get();

        List<CommentEntity> commentEntityList =
                commentRepository.findAllByBoardEntityOrderByCommentIdDesc(boardEntity);

        List<CommentDto> commentDtoList = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntityList){
            CommentDto commentDto = CommentDto.toCommentDto(commentEntity, boardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
    // 댓글 삭제
    public int commetDeleteDo(Long commentId, String email) {
        //현재로그인 한 회원 아이디를 가져온다.
        Long mId = memberRepository.findBymEmail(email).get().getMId();
        //댓글 작성자의 정보를 가져온다.
        Long mId2 = commentRepository.findById(commentId).get().getMemberEntity().getMId();
        if (mId == mId2) {
            //로그인 한 아이디랑 해당 댓글 작성자의 아이디가 같으면 댓글 삭제 실행
            commentRepository.deleteById(commentId);
        }
        if (commentRepository.findById(commentId).isEmpty()) {
            return 1;
        }
        return 0;
    }
    
    //게시글 찾기
    public Long findBoardId(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        Long boarId = commentEntity.getBoardEntity().getBoardId();

        return boarId;

    }
    
    //댓글 찾기
    public CommentDto findByCommet(Long commentId) {

        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentId);

        if (optionalCommentEntity.isPresent()){
            return CommentDto.toCommentUpdateDo(optionalCommentEntity.get());
        }
        return null;
    }
    
    //댓글 수정
    @Transactional
    public void commentUpdateDo(CommentDto commentDto, String mEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findBymEmail(mEmail);
        MemberEntity memberEntity = optionalMemberEntity.get();

        Optional<BoardEntity> optionalBoardEntity = boardReposistory.findById(commentDto.getBoardId());
        BoardEntity boardEntity = optionalBoardEntity.get();

        CommentEntity commentEntity = CommentEntity.toUpdateEntity(commentDto,memberEntity,boardEntity);
        commentRepository.save(commentEntity);

    }
}

```

</details>

<details>
<summary>버스정보 조회 구현</summary>

![image](https://user-images.githubusercontent.com/116870683/234753989-980303e6-41e0-40dd-bb35-3fd3b135bdba.png)

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>공공데이터포털 버스 API</title>
    <link rel="stylesheet" th:href="@{/css/bus/busIndex.css}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script th:src="@{/js/bus/busIndex.js}" defer></script>

</head>

<body>
<div th:replace="~{fragment/common/adminHeader :: adminHeaderFragment}"></div>
<div id="wrap">
    <div th:replace="~{fragment/common/leftMenu :: leftMenuFragment}"></div>
    <div class="bus">
        <div class="bus-con">
            <div class="bus-header">
                <h1>공공데이터포털 버스 API</h1>
                <input type="text" name="search" id="search" placeholder="노선(버스)번호를 입력하세요">
                <input type="button" value="버스노선검색"
                       onclick="Search1()">
            </div>
            <div class="bus-detail">
                <table>
                    <thead>
                    <tr>
                        <th>노선명</th>
                        <th>노선유형</th>
                        <th>노선거점</th>
                        <th>노선종점</th>
                        <th>첫차시간</th>
                        <th>막차시간</th>
                        <th>배차간격</th>
                        <th>정류장정보조회</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="map-con">
                <div class="stationNm">
                </div>
                <div id="mapview" style="width:100px; height: 500px; "></div>
                <script type="text/javascript"
                        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=56636de0137b086887bf183725e64d3c"></script>
            </div>
        </div>
    </div>
</div>
</body>

</html>


```



```javascript

let url = 'https://cors-anywhere.herokuapp.com/http://ws.bus.go.kr/api/rest/';
const buskey = '5fU%2BwgB7qluAY%2FryjtTo9rfo4grvFw70mJtANFSbNBxTtBpACIlixoL%2F%2FdvMYfS2QKsI0H8LF9UwXbiFsM%2Flwg%3D%3D';
//<!-- 버스 노선 검색 -->
//<!--        var url = 'https://cors-anywhere.herokuapp.com/http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?serviceKey=5fU%2BwgB7qluAY%2FryjtTo9rfo4grvFw70mJtANFSbNBxTtBpACIlixoL%2F%2FdvMYfS2QKsI0H8LF9UwXbiFsM%2Flwg%3D%3D&strSrch=1119&resultType=json'-->


//   var xhr = new XMLHttpRequest();-->
//   var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'5fU%2BwgB7qluAY%2FryjtTo9rfo4grvFw70mJtANFSbNBxTtBpACIlixoL%2F%2FdvMYfS2QKsI0H8LF9UwXbiFsM%2Flwg%3D%3D'; /*Service Key*/-->
//    queryParams += '&' + encodeURIComponent('busRouteId') + '=' + encodeURIComponent('100100025') + '&resultType=json'; /**/
//   xhr.open('GET', url + queryParams);-->
//   xhr.onreadystatechange = function () {  -->
//   if (this.readyState == 4) {-->
//       alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
//    }
//   };



// xhr.send('');
// function fn1(){

//     fetch(apiurl)
//     .then(response => response.json())
//       .then(function (msg) {
//                console.log(msg)
//                console.log(msg.msgBody)
//                console.log(msg.msgBody.itemList)

//            msg.msgBody.itemList.forEach(el=>{
//               console.log(el);

//               })
//        });
// }


//     (() => {
//     fn1();
//     })();

const busDetail = document.querySelector('.bus-detail')
const tbody = document.querySelector('.bus-detail table tbody')
const stationNm = document.querySelector('.map-con .stationNm')
let html1 = "";

//버스노선(버스번호) 조회
function Search1() {
    html1 = "";
    tbody.innerHTML = "";

    var search = document.querySelector('#search')
    var type = 'busRouteInfo/getBusRouteList?';
    var strSrch = search.value;
    console.log(strSrch + ' < - strSrch')

    var apiUrl = `${url}busRouteInfo/getBusRouteList?serviceKey=${buskey}&strSrch=${strSrch}&resultType=json`;
//    let apiUrl = `${url}${type}serviceKey=${buskey}&strSrch=${strSrch}&resultType=json`;



    fetch(apiUrl)
        .then(response => response.json())
        .then(function (msg) { //아래부터는 html로 가져오기 위한 코드-->
            if (msg.msgBody.itemList == null) {
                alert("조회 실패!! 해당노선이 존재하지 않습니다.")
            }
            else {
                alert("조회 성공!!")
                console.log(msg)
                console.log(msg.msgBody)
                console.log(msg.msgBody.itemList)

                msg.msgBody.itemList.forEach(el => {
                    html1 += "<tr>"
                    console.log(el);
                    console.log(el.gpsX, el.gpsY, el.stationNm); // kakao map 표시-->

                    //<div>버스명: ${el.busRouteNm}</div>
                    //<div onclick='stationPost(event.target.innerText)'>${el.busRouteId}</div>
                    html1 += `
                                    <td> ${el.busRouteNm}</td>
                                    <td> ${el.routeType}</td>
                                    <td> ${el.stStationNm}</td>
                                    <td> ${el.edStationNm}</td>
                                    <td> ${el.firstBusTm}</td>
                                    <td> ${el.lastBusTm}</td>
                                    <td> ${el.term} </td>
                                    <td onclick='stationPost(event.target.innerText)'> ${el.busRouteId}</td>
                              `;
                    html1 += "</tr>"
                })
                console.log(html1)
                tbody.innerHTML += html1
            }
        });

}

//정류장 조회
function stationPost(busId){

    let html1="";

    let busRouteId=busId;
    let apiUrl = `${url}busRouteInfo/getStaionByRoute?serviceKey=${buskey}&busRouteId=${busRouteId}&resultType=json`;

    fetch(apiUrl)
        .then(response => response.json())
        .then(function (msg){



            console.log(msg) // -> 로그찍히는지 확인
            console.log('----------')
            console.log(msg.msgBody) // -> 로그찍히는지 확인
            console.log('----------')
            console.log(msg.msgBody.itemList) // -> 로그찍히는지 확인
            console.log('----------')

            msg.msgBody.itemList.forEach(el=>{
                console.log(el.gpsX,el.gpsY)
            html1+=`<div>${el.stationNm}</div>`
            // let lat = el.gpsX; //정류장 위도
            // let lon = el.gpsy; //정류장 경도


            })

            stationNm.innerHTML=html1;
            busmap(msg.msgBody.itemList);

        });

}



function busmap(data){
    console.log(data);
    console.log('<<<');

    let locations = [];

    let lat = 0;
    let lon = 0;

    console.log('위도: '+lat, '경도: '+lon)
    console.log('<<<');

    data.forEach((el,index) =>{
        lat = el.gpsY;
        lon = el.gpsX;

        let result = {
            title: el.stationNm,
            // parseFloat()함수는 문자열 인수를 구문 분석하고 부동 소수점 숫자를 반환
            latlng: new kakao.maps.LatLng(parseFloat(lat), parseFloat(lon))
        };
        locations.push(result);
    });

    let mapContainer = document.getElementById('mapview'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(data[0].gpsY, data[0].gpsX),
        level: 5 // 지도의 확대 레벨
    };

// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다.
let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
console.log(data[0].gpsY, data[0].gpsX);

// 주소-좌표 변환 객체를 생성합니다.
// let geocoder = new kakao.maps.services.Geocoder();

   // 마커 이미지의 이미지 주소입니다
   let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    console.log('------------------');
    console.log(data[0].gpsY, data[0].gpsX);

for (let i = 0; i < data.length; i++) {
    // 마커 이미지의 이미지 크기 입니다.
    let imageSize = new kakao.maps.Size(24,35);
    // 마커 이미지를 생성합니다.
    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    //마커를 생성합니다.
    let marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: locations[i].latlng, // 마커를 표시할 위치
        title: locations[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage // 마커 이미지
      });

    marker.setMap(map);//마커생성

}

map.setCenter(locations[0].latlng);


}
```
</details>
