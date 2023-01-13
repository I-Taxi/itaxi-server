package com.itaxi.server.schedule;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.repository.KTXRepository;
import com.itaxi.server.post.domain.Post;
import com.itaxi.server.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@Service
@RequiredArgsConstructor
public class Scheduller {
    private final PostRepository postRepository;
    private final KTXRepository ktxRepository;

    //@Scheduled(fixedRate = 30000) // 테스트용 (30초로 설정)
    @Scheduled(cron = "0 0 * * * *") // 매시 0분에 동작하도록 설정
    public void deletePostByTime() {
        // 현재 Post와 KTX중에서 deleted = 0인것들을 가져온다
        List<Post> postList = postRepository.findByDeleted(false);
        List<KTX> ktxList = ktxRepository.findByDeleted(false);
        LocalDateTime time = LocalDateTime.now();

        // Post와 KTX를 담고 있는 List들을 iterate하며 현재 시간과 비교하여 시간이 지난 채팅방들을 삭제한다.
        for (Post post : postList) {
            if (time.isAfter(post.getDeptTime())) {
                post.setDeleted(true);
                postRepository.save(post);
            }
        }
        for (KTX ktx : ktxList) {
            if (time.isAfter(ktx.getDeptTime())) {
                ktx.setDeleted(true);
                ktxRepository.save(ktx);
            }
        }
    }
}
