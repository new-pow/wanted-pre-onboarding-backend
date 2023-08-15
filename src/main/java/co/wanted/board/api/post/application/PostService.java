package co.wanted.board.api.post.application;

import co.wanted.board.api.post.application.exception.ErrorCode;
import co.wanted.board.api.post.application.exception.PostException;
import co.wanted.board.api.post.domain.Post;
import co.wanted.board.api.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(Long memberId, String title, String contents) {
        return postRepository.save(Post.of(title, memberId, contents));
    }
}
