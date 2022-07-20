package com.itaxi.server.post.application;

import com.itaxi.server.exception.post.JoinerDuplicateMemberException;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.exception.post.PostNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.application.dto.JoinerCreateDto;
import com.itaxi.server.post.application.dto.PostJoinDto;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.repository.JoinerRepository;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.presentation.response.PostInfoResponse;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.member.domain.dto.MemberJoinInfo;
import com.itaxi.server.post.domain.dto.PostLog;
import com.itaxi.server.post.domain.dto.PostLogDetail;
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
    private final JoinerRepository joinerRepository;

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

    public Post create(PostDto.AddPostPlaceReq dto) {
        return postRepository.save(dto.toEntity());
    }

    public PostInfoResponse joinPost(Long postId, PostJoinDto postJoinDto) {
        Post postInfo = null;
        Member memberInfo = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
            postInfo.setStatus(postJoinDto.getStatus());
            postRepository.save(postInfo);
        } else {
            throw new PostNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Member> member = memberRepository.findMemberByUid(postJoinDto.getUid());
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (!joiner.isPresent()) {
            JoinerCreateDto joinerCreateDto = new JoinerCreateDto(memberInfo, postInfo, postJoinDto.getLuggage(), postJoinDto.isOwner());
            joinerRepository.save(new Joiner(joinerCreateDto));
        } else {
            throw new JoinerDuplicateMemberException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return postInfo.toPostInfoResponse();
    }

    public String exitPost(Long postId, String uid) {
        Post postInfo = null;
        Member memberInfo = null;

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postInfo = post.get();
        } else {
            throw new PostNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if (member.isPresent()) {
            memberInfo = member.get();
        } else {
            throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Joiner> joiner = joinerRepository.findJoinerByPostAndMember(postInfo, memberInfo);
        if (joiner.isPresent()) {
            Joiner joinerInfo = joiner.get();
            joinerInfo.setStatus(0);
            joinerInfo.setDeleted(true);
            joinerRepository.save(joinerInfo);
        } else {
            return "Failed";
        }

        return "Success";
    }
}