package miu.edu.upvote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUpvoteDAO extends JpaRepository<Upvote,Long> {
    public Optional<Upvote> findByArticleId(Long articleId);

    public Optional<Upvote> findByUserId(Long userId);
}
