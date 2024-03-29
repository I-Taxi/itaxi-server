package com.itaxi.server.favorite.application;


import com.itaxi.server.exception.favorite.FavorBadTypeException;
import com.itaxi.server.exception.favorite.FavorDuplicateException;
import com.itaxi.server.exception.favorite.FavorNoAuthorityException;
import com.itaxi.server.exception.favorite.FavorNotFoundException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.favorite.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.favorite.application.dto.FavorJoinerInfo;
import com.itaxi.server.favorite.application.dto.FavorJoinerInfoForRead;
import com.itaxi.server.favorite.application.dto.FavorJoinerSaveDto;
import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.favorite.presentation.request.FavorDeleteRequest;
import com.itaxi.server.favorite.presentation.request.FavorReadRequest;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.favorite.domain.repository.FavorJoinerRepository;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.member.presentation.response.FavorJoinerReadAllResponse;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavorJoinerService {

    private final FavorJoinerRepository favorJoinerRepository;
    private final PlaceRepository placeRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public String createFavorite(FavorJoinerCreateDto dto) {
        boolean confirm = false;
        Optional<Member> member =  memberRepository.findMemberByUid(dto.getUid());
        Optional<Place> place = placeRepository.findById(dto.getPlaceId());

        if(!place.isPresent()) throw new PlaceNotFoundException();
        if(!member.isPresent()) throw new MemberNotFoundException();
        if(member.get().isDeleted()) throw new MemberNotFoundException();
        FavorJoinerInfo check = new FavorJoinerInfo(member.get());

        for(int i = 0; i < check.getJoiner().size(); i++){
            FAVORJoiner j = check.getJoiner().get(i);
            if(j.getPlace().getId() == place.get().getId()){
                if(j.isDeleted() == false){
                    confirm = true;
                    break;
                }
                else{
                    j.setDeleted(false);
                    favorJoinerRepository.save(j);
                    return "Success";
                }
            }
        }

        if(confirm == true) throw new FavorDuplicateException();
        FavorJoinerSaveDto saveDto = new FavorJoinerSaveDto(member.get(),place.get());
        FAVORJoiner favorJoiner = favorJoinerRepository.save(new FAVORJoiner(saveDto));

        return "Success";
    }

    @Transactional
    public List readAllFavorByMember(FavorReadRequest request) {
        String uid = request.getUid();
        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent()) throw new MemberNotFoundException();
        if(member.get().isDeleted()) throw new MemberNotFoundException();
        if(request.getFavorType() !=0 && request.getFavorType()!=1) throw new FavorBadTypeException();
        FavorJoinerInfoForRead favorJoinerInfo = new FavorJoinerInfoForRead(member.get(), request.getFavorType());
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
    public String deleteFavorite(Long Id, FavorDeleteRequest request) {
        Optional<FAVORJoiner> favorJoiner = favorJoinerRepository.findById(Id);

        if(!favorJoiner.isPresent()) throw new FavorNotFoundException();
        else if(favorJoiner.get().isDeleted() == true) throw new FavorNotFoundException();
        if(!(favorJoiner.get().getMember().getUid().equals(request.getUid()))) throw
            new FavorNoAuthorityException();

        if(favorJoiner.isPresent()){
            FAVORJoiner favorInfo = favorJoiner.get();
            favorInfo.setDeleted(true);
            favorJoinerRepository.save(favorInfo);
        }
        return "Success";
    }

}
