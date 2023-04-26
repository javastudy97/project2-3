<p align="center">
  <img src="https://user-images.githubusercontent.com/116870617/231791531-9e7ee801-a462-4b7a-977d-1e56b195e28b.png">
</p>

# KICKOFF (킥오프)
## Open API와 챗봇을 활용한 축구 동호회 운영/관리 그룹웨어 
## [프로젝트 소개 PDF(영상포함)](https://drive.google.com/file/d/1hKRz8V7S5cDmN2NThJtAIUazAXCb3UvG/view)
## [프로젝트 DB 명세서](https://drive.google.com/file/d/187f50y_fUM-Pkhs0hEeW8VLSJVCvn7U-/view?usp=share_link)
## [프로젝트 DB ERD](https://drive.google.com/file/d/1JvddvhrdoV1i78wkrrgPDKJa1tq9k7LW/view?usp=share_link)

## 📅 프로젝트 기간 - 2023.03.14 ~ 2023.04.06

## 🖥️ 프로젝트 소개
- Open API(우편번호, 날씨, 일정관리, 조직관리 등)를 활용한 동호회 운영/관리용 그룹웨어입니다.
- 일정, 결재, 회계, 공지사항, 게시판 등 조직관리에 필요한 전반적인 기능들을 구현하였습니다.
- 기능 단위별로 패키지를 나누어 merge시 git 충돌을 최소화 하였습니다. 
- 네이티브쿼리 사용시 발생할 수 있는 컬럼인식 오류를 방지하고, 가급적 JPA의 기본 쿼리메소드(JPQL)만으로 비즈니스 로직이 구현 가능하도록 테이블간 연관관계를 설정하였습니다.
- Komoran(한국어 형태소 분석기)을 활용한 시나리오형 챗봇을 구현하였습니다.
- Github Actions와 AWS EC2를 기반으로 CI/CD(지속통합/지속배포) 환경을 구축하였습니다.

## ⚙️ 개발 환경
- `Language` : Java 11, HTML5, CSS3, JavaScript
- `IDE` : IntelliJ IDEA, Visual Studio Code
- `Framework` : Springboot
- `Database` : MySQL
- `Template Engine` : Thymeleaf 
- `ORM` : JPA <br>

## 🧑‍🤝‍🧑 팀 구성 및 역할
#### 👨‍💻 팀장 : 김필수 <br>
#### `java package` : config / member / admin / entity / dto <br>
#### `templates package` : login / join / index / member / admin <br>
- 프로젝트 일정 관리 및 발표준비
- 소스 통합 및 형상관리
  
- 날씨(Openweathermap), 우편번호(다음 우편번호) API 연동
- 결재서류 승인/반려 처리 구현 <br>

#### 👨‍💻 팀원 : 김현기 <br>
#### `java package` : account / naver <br>
#### `templates package` : account / naver <br>
- 회계내역(수입/지출) CRUD 구현
- Naver API(workplace, work) 연동 <br>

#### 👨‍💻 팀원 : 김홍록 <br>
#### `java package` : notice / board / comment <br>
#### `templates package` : notice / board <br>
- 공지사항, 커뮤니티 게시판 CRUD 구현
- 공통요소(fragment) 디자인
- Github Actions, AWS EC2 기반 CI/CD 구현 <br>

####공지사항, 커뮤니티 게시판 CRUD 구현 ###

#### BoardController


private final BoardService boardService;
    private final CommentService commentService;

+ __게시판 조회페이지__


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


#### 👨‍💻 팀원 : 이정모 <br>
#### `java package` : calender <br>
#### `templates package` : calender <br>
- 근태관리 구현
- 공식일정, 개인일정 CRUD 구현 
- 일정관리(fullcalender) API 연동 <br>

#### 👨‍💻 팀원 : 장기운 <br>
#### `java package` : member / approval <br>
#### `templates package` : member / approval / management  <br>
- 결재관리 CRUD 구현
- 조직관리 CRUD 구현
- Komoran 기반 챗봇 구현 <br>
