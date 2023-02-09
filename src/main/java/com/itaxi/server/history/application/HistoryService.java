package com.itaxi.server.history.application;

import com.itaxi.server.exception.history.HistoryBadTypeException;
import com.itaxi.server.exception.ktx.KTXNoAuthorityException;
import com.itaxi.server.exception.ktx.KTXNotFoundException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.post.PostNoAuthorityException;
import com.itaxi.server.exception.post.PostNotFoundException;
import com.itaxi.server.history.application.dto.HistoryLog;
import com.itaxi.server.history.application.dto.HistoryLogDetail;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.repository.KTXRepository;
import com.itaxi.server.member.application.dto.MemberJoinInfo;
import com.itaxi.server.member.application.dto.MemberKTXJoinInfo;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.repository.PostRepository;
import com.itaxi.server.post.presentation.request.PostGetLogDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final KTXRepository ktxRepository;

    @Transactional
    public List<HistoryLog> getPostLog(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent()) {
            throw new MemberNotFoundException();
        }
        if(member.get().isDeleted())
            throw new MemberNotFoundException();

        MemberJoinInfo joinInfo = new MemberJoinInfo(member.get());
        MemberKTXJoinInfo ktxJoinInfo = new MemberKTXJoinInfo(member.get());

        List<HistoryLog> historyLogs = new ArrayList<>();
        PriorityQueue<HistoryLog> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(Post post : joinInfo.getPosts()) {
            pQueue.add(new HistoryLog(post));
        }
        for (KTX ktx : ktxJoinInfo.getKtxes()) {
            pQueue.add(new HistoryLog(ktx));
        }
        while(pQueue.size() > 0) {
            historyLogs.add(pQueue.poll());
        }
        return historyLogs;
    }

    @Transactional
    public HistoryLogDetail getLogDetail(Long type,Long postId, PostGetLogDetailRequest request) {
        if(type == 0){
            Optional<Post> post = postRepository.findById(postId);

            if(!post.isPresent()) {
                throw new PostNotFoundException();
            }

            boolean check = false;
            for(int i =  0; i<post.get().getJoiners().size(); i++){
                if(post.get().getJoiners().get(i).getMember().getUid().equals(request.getUid())){
                    check = true;
                }
            }

            if(check == false ){
                throw new PostNoAuthorityException();
            }
            return new HistoryLogDetail(post.get());
        }
        else if(type == 1){
            Optional<KTX> ktx = ktxRepository.findById(postId);

            if (!ktx.isPresent()) {
                throw new KTXNotFoundException();
            }

            boolean check = false;
            for(int i =  0; i < ktx.get().getJoiners().size(); i++){
                if(ktx.get().getJoiners().get(i).getMember().getUid().equals(request.getUid())){
                    check = true;
                }
            }

            if(check == false){
                throw new KTXNoAuthorityException();
            }

            return new HistoryLogDetail(ktx.get());
        }
        else{
            throw new HistoryBadTypeException();
        }


    }
}
