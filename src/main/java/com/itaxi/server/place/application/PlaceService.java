package com.itaxi.server.place.application;
import com.itaxi.server.cheaker.AdminChecker;
import com.itaxi.server.exception.member.MemberNotAdminException;
import com.itaxi.server.exception.place.*;
import com.itaxi.server.place.application.dto.AddPlaceDto;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.presentation.reponse.PlaceFindResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final AdminChecker adminChecker;

    @Transactional
    public List<PlaceFindResponse> ReadAll(int findType) {
        List<PlaceFindResponse> result = new ArrayList<>();

        if(findType==0){
            for(Place place :placeRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"))){
                if(place !=null &&place.getPlaceType()!=3 && place.getPlaceType()!=4){
                    result.add(0, new PlaceFindResponse(place));
                }
            }

        }
        else if(findType==1){
            for(Place place :placeRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"))){
                result.add(0, new PlaceFindResponse(place));
            }
        }
        else{
            throw new PlaceBadFindTypeException();
        }

        return result;
    }

    @Transactional
    public Place create(AddPlaceDto dto) {
        if (dto.getCnt() < 0) throw new PlaceBadCntException();
        if (dto.getPlaceType() < 0 || dto.getPlaceType() >= 5) throw new PlaceBadTypeException();
        if (dto.getName() == null || dto.getName()==""|| dto.getName().equals(" ")) throw new PlaceEmptyException();


        Place savedPlace = null;
        if (adminChecker.isAdmin(dto.getUid())) {
            savedPlace = placeRepository.save(dto.toEntity());
        } else {
            throw new MemberNotAdminException();
        }

        return savedPlace;
    }

    @Transactional
    public Place updatePlace(long id, UpdatePlaceDto dto) {
        if (dto.getPlaceType() < 0 || dto.getPlaceType() >= 5) throw new PlaceBadTypeException();
        if (dto.getName() == null || dto.getName() == "" || dto.getName().equals(" ")) throw new PlaceEmptyException();
        final Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);

        if (adminChecker.isAdmin(dto.getUid())) {
            place.updatePlace(dto);
        } else {
            throw new MemberNotAdminException();
        }

        return place;
    }

    @Transactional
    public String deletePlace(Long id, String uid) {
        Place del = null;

        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            del = place.get();
            del.setDeleted(true);
        } else {
            throw new PlaceNotFoundException();
        }

        if (adminChecker.isAdmin(uid)) {
            placeRepository.save(del);
        } else {
            throw new MemberNotAdminException();
        }

        return "Success";
    }

    @Transactional
    public Long updateView(Long id) {
        Place placeInfo  = null;
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            placeInfo = place.get();
            placeInfo.setCnt(placeInfo.getCnt() + 1);
            placeRepository.save(placeInfo);
        } else {
            throw new PlaceNotFoundException();
        }

        return placeInfo.getCnt();
    }
}

