package com.itaxi.server.member.application;


import com.itaxi.server.exception.member.FavorDuplicatedException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.member.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.member.application.dto.FavorJoinerInfo;
import com.itaxi.server.member.application.dto.FavorJoinerSaveDto;
import com.itaxi.server.member.domain.FavorJoiner;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.FavorJoinerRepository;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.member.presentation.response.FavorJoinerReadAllResponse;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FavorJoinerService {

    private final FavorJoinerRepository favorJoinerRepository;
    private final PlaceRepository placeRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public String create(FavorJoinerCreateDto dto) {

        boolean confirm = false;
        Optional<Member> member =  memberRepository.findMemberByUid(dto.getMemberUid());
        Optional<Place> place = placeRepository.findById(dto.getPlaceId());

        if(!place.isPresent()) throw new PlaceNotFoundException();
        if(!member.isPresent()) throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        FavorJoinerInfo check = new FavorJoinerInfo(member.get());

        for(Place p : check.getPlaces()){
            if(p.getId()==place.get().getId()) {
                confirm = true;
                break;
            }
        }
        if(confirm==true) throw new FavorDuplicatedException(HttpStatus.INTERNAL_SERVER_ERROR);
        FavorJoinerSaveDto saveDto = new FavorJoinerSaveDto(member.get(),place.get());
        FavorJoiner favorJoiner = favorJoinerRepository.save(new FavorJoiner(saveDto));

        return "Success";
    }

    @Transactional
    public List readAllFavorByMember(String uid) {
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent()) throw new MemberNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        FavorJoinerInfo favorJoinerInfo = new FavorJoinerInfo(member.get());
        List<FavorJoinerReadAllResponse> result = new ArrayList<>();
        PriorityQueue<FavorJoinerReadAllResponse> pQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i  = 0; i<favorJoinerInfo.getPlaces().size(); i++){
            pQueue.add(new FavorJoinerReadAllResponse(favorJoinerInfo.getPlaces().get(i),favorJoinerInfo.getJoinerId().get(i)));
        }
        while(pQueue.size() > 0)
            result.add(pQueue.poll());


        return result;

    }

    @Transactional
    public String delete(Long Id) {
        Optional<FavorJoiner> favorJoiner = favorJoinerRepository.findById(Id);
        if(favorJoiner.isPresent()){
            FavorJoiner favorInfo = favorJoiner.get();
            favorInfo.setDeleted(true);
            favorJoinerRepository.save(favorInfo);
        }
        return "Success";
    }

}
