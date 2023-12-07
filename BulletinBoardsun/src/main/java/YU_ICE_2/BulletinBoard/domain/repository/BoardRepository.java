package YU_ICE_2.BulletinBoard.domain.repository;
import YU_ICE_2.BulletinBoard.domain.entry.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
