package YU_ICE_2.BulletinBoard.service;
import YU_ICE_2.BulletinBoard.DTO.BoardDTO;
import YU_ICE_2.BulletinBoard.domain.entry.Board;
import YU_ICE_2.BulletinBoard.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository)
    {
        this.boardRepository=boardRepository;
    }
    public Long savePost(BoardDTO boardDTO)
    {
        return boardRepository.save(boardDTO.toEntity()).getId();
    }
    public List<BoardDTO>getBoardList()
    {
        List<Board>boardList=boardRepository.findAll();
        List<BoardDTO>boardDTOList=new ArrayList<>();
        for(Board board :boardList){
            BoardDTO boardDTO=BoardDTO.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
    public BoardDTO getMyBoard(long id)
    {
        Board mybd=boardRepository.findById(id).orElse(null);
        BoardDTO build=BoardDTO.builder().id(mybd.getId())
                .author(mybd.getAuthor())
                .title(mybd.getTitle())
                .content(mybd.getContent())
                .createdDate(mybd.getCreatedDate())
                .modifiedDate(mybd.getModifiedDate())
                .build();
        return build;
    }
    public void deleteByld(Long id)
    {
        boardRepository.deleteById(id);
    }
}
