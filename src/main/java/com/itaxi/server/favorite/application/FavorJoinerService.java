package com.itaxi.server.favorite.application;


import com.itaxi.server.exception.favorite.FavorBadTypeException;
import com.itaxi.server.exception.favorite.FavorDuplicateException;
import com.itaxi.server.exception.favorite.FavorNoAuthorityException;
import com.itaxi.server.exception.favorite.FavorNotFoundException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.favorite.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.favorite.application.dto.FavorJoinerInfo;
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
        boolean del = false;
        Optional<Member> member =  memberRepository.findMemberByUid(dto.getUid());
        Optional<Place> place = placeRepository.findById(dto.getPlaceId());

        if(!place.isPresent()) throw new PlaceNotFoundException();
        if(!member.isPresent()) throw new MemberNotFoundException();
        if(member.get().isDeleted()) throw new MemberNotFoundException();
        if(!(dto.getFavorType()==0) && !(dto.getFavorType()==1)) throw new FavorBadTypeException();
        FavorJoinerInfo check = new FavorJoinerInfo(member.get(),dto.getFavorType());

        for(int i = 0; i<check.getJoiner().size(); i++){
            Place p = check.getJoiner().get(i).getPlace();
            if(p.getId()==place.get().getId()) {
                if(check.getJoiner().get(i).isDeleted()==false){
                    confirm = true;
                    break;
                }
                else{
                    FAVORJoiner save = check.getJoiner().get(i);
                    save.setDeleted(false);
                    favorJoinerRepository.save(save);
                    return "Success";
                }
            }
        }

        if(confirm == true) throw new FavorDuplicateException();
        FavorJoinerSaveDto saveDto = new FavorJoinerSaveDto(member.get(),place.get(),dto.getFavorType());
        FAVORJoiner favorJoiner = favorJoinerRepository.save(new FAVORJoiner(saveDto));

        return "Success";
    }

    @Transactional
    public List readAllFavorByMember(FavorReadRequest request) {
        String uid = request.getUid();
        int favorType = request.getFavorType();

        Optional<Member> member = memberRepository.findMemberByUid(uid);
        if(!member.isPresent()) throw new MemberNotFoundException();
        if(member.get().isDeleted()) throw new MemberNotFoundException();
        FavorJoinerInfo favorJoinerInfo = new FavorJoinerInfo(member.get(),favorType);
        List<FavorJoinerReadAllResponse> result = new ArrayList<>();
        PriorityQueue<FavorJoinerReadAllResponse> pQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i  = 0; i<favorJoinerInfo.getPlaces().size(); i++){
            if(favorJoinerInfo.getJoiner().get(i).isDeleted() == false) {
                FAVORJoiner f = favorJoinerInfo.getJoiner().get(i);
                pQueue.add(new FavorJoinerReadAllResponse(f.getPlace(),f.getId(),f.getFavorType()));
            }
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
