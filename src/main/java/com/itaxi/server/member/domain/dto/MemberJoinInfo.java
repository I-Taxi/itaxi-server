package com.itaxi.server.member.domain.dto;

import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberJoinInfo {

    private final List<Post> posts;

    public MemberJoinInfo(Member m) {
        List<Joiner> joiners = m.getJoiners();
        this.posts = new ArrayList<>();
        for(Joiner joiner : joiners) {
            if(joiner.getStatus() == 1)
                posts.add(joiner.getPost());
        }
    }
}