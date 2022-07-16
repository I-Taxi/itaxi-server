package com.itaxi.server.post.application;

import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.post.PostNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.dto.MemberJoinInfo;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.dto.PostLog;
import com.itaxi.server.post.domain.dto.PostLogDetail;
import com.itaxi.server.post.domain.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /* CREATE */

    /* READ */
    public List<PostLog> getPostLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(member.isPresent()) {
            MemberJoinInfo joinInfo = new MemberJoinInfo(member.get());
            List<PostLog> postLogs = new ArrayList<>();
            for(Post post : joinInfo.getPosts())
                postLogs.add(new PostLog(post));
            return postLogs;
        }
        throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public PostLogDetail getPostLogDetail(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent())
            return new PostLogDetail(post.get());
        throw new PostNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* UPDATE */

    /* DELETE */

}