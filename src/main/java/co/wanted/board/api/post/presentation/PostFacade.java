package co.wanted.board.api.post.presentation;

import co.wanted.board.api.auth.presentation.dto.Logined;
import co.wanted.board.api.member.application.SearchService;
import co.wanted.board.api.member.application.exception.ErrorCode;
import co.wanted.board.api.member.application.exception.MemberException;
import co.wanted.board.api.member.domain.Member;
import co.wanted.board.api.post.application.PostService;
import co.wanted.board.api.post.application.exception.PostException;
import co.wanted.board.api.post.domain.Post;
import co.wanted.board.api.post.presentation.dto.PostSelect;
import co.wanted.board.api.post.presentation.dto.PostUpdate;
import co.wanted.board.api.post.presentation.dto.PostWrite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static co.wanted.board.api.member.application.exception.ErrorCode.NO_SUCH_MEMBER;
import static co.wanted.board.api.post.application.exception.ErrorCode.ANOTHER_AUTHOR;

@Component
@RequiredArgsConstructor
public class PostFacade {

    private final SearchService searchService;
    private final PostService postService;

    public PostWrite.Response writePost(Logined logined, PostWrite.Request request) {
        if (!searchService.isExistMember(logined.getMemberId())) {
            throw new MemberException(NO_SUCH_MEMBER);
        }
        Post savedPost = postService.write(logined.getMemberId(), request.getTitle(), request.getContents());
        return PostWrite.Response.of(savedPost);
    }
}
