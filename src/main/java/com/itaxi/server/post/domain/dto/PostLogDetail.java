package com.itaxi.server.post.domain.dto;

import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.post.domain.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostLogDetail extends PostLog {
    private List<JoinerInfo> joiners;

    public PostLogDetail(Post p) {
        super(p);
        joiners = new ArrayList<>();
//        for(Joiner joiner : p.getJoiners())
//            joiners.add(new JoinerInfo(joiner));
    }
}
