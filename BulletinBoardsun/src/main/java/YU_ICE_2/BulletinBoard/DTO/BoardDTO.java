package YU_ICE_2.BulletinBoard.DTO;
import YU_ICE_2.BulletinBoard.domain.entry.Board;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity()
    {
        Board build=Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDTO(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate)
    {
        this.id=id;
        this.author=author;
        this.title=title;
        this.content=content;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
    }
}
