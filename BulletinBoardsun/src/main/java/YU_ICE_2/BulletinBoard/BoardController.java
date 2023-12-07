package YU_ICE_2.BulletinBoard;

import YU_ICE_2.BulletinBoard.DTO.BoardDTO;
import YU_ICE_2.BulletinBoard.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class BoardController {
    private BoardService boardService;

    private final Logger log=LoggerFactory.getLogger(getClass());
    public BoardController(BoardService boardService)
    {
        this.boardService=boardService;
    }

    @GetMapping("/")
    public String list(Model model)
    {
        List<BoardDTO>boardDTOList=boardService.getBoardList();
        model.addAttribute("postList",boardDTOList);
        return "board/list.html";
    }
    @GetMapping("/post")
    public String post()
    {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDTO boardDTO)
    {
        System.out.println(boardDTO.getCreatedDate());
        boardService.savePost(boardDTO);
        return "redirect:/";
    }
    @GetMapping("/correction")
    public String correct(@RequestParam("id")Long id,Model model) {
        BoardDTO boardDTOList=boardService.getMyBoard(id); String my_id=id.toString();
        log.info(my_id); model.addAttribute("postList",boardDTOList);
        return "board/correction.html"; }
    @PostMapping("/correction") @Transactional
    public String update(BoardDTO boardDTO) {
        Long my_id=boardDTO.getId(); BoardDTO myBoard=new BoardDTO(); String temp=my_id.toString();
        myBoard = boardService.getMyBoard(my_id); if(myBoard!=null)
        {
            myBoard.setAuthor(boardDTO.getAuthor()); myBoard.setId(boardDTO.getId()); myBoard.setContent(boardDTO.getContent()); myBoard.setModifiedDate(boardDTO.getModifiedDate()); myBoard.setTitle(boardDTO.getTitle());
        } boardService.savePost(myBoard); return "redirect:/";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id")Long id)
    {
        boardService.deleteByld(id);
        return "redirect:/";
    }

}
