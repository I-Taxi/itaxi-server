package com.itaxi.server.place.application;

import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import com.itaxi.server.exception.bannerPlace.BannerPlaceCntMinusException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotFoundException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotNullException;
import com.itaxi.server.exception.place.PlaceCntMinusException;
import com.itaxi.server.exception.place.PlaceNotNullException;
import com.itaxi.server.exception.place.PlaceTypeMinusException;
import com.itaxi.server.notice.presentation.response.NoticeReadAllResponse;
import com.itaxi.server.place.application.dto.AddPlaceDto;
import com.itaxi.server.place.application.dto.DeletePlaceDto;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public Iterable<Place> findPlaceForLookUp() {
        return placeRepository.findAll(Sort.by(Sort.Direction.DESC, "placetype"));
    }

    @Transactional
    public List<Place> findPlaceForRecruit() {
        List<Place> result = new ArrayList<>();
        for(Place place :placeRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"))){
            if(place !=null &&place.getPlacetype()!=3 && place.getPlacetype()!=4){
                result.add(0, new Place(place.getName(),place.getCnt(), place.getPlacetype()));
            }
        }
        return result;
    }

    @Transactional
    public Place create(AddPlaceDto dto) {
        if(dto.getCnt()<0) throw new PlaceCntMinusException(HttpStatus.INTERNAL_SERVER_ERROR);
        if(dto.getPlacetype()<0||dto.getPlacetype()>=5) throw new PlaceTypeMinusException(HttpStatus.INTERNAL_SERVER_ERROR);
        if(dto.getName()==null || dto.getName()==""|| dto.getName().equals(" ")) throw new PlaceNotNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        return placeRepository.save(dto.toEntity());
    }

    @Transactional
    public Place updatePlace(long id, UpdatePlaceDto dto) {
        if(dto.getPlacetype()<0||dto.getPlacetype()>=5) throw new PlaceTypeMinusException(HttpStatus.INTERNAL_SERVER_ERROR);
        if(dto.getName()==null || dto.getName()==""|| dto.getName().equals(" ")) throw new PlaceNotNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        final Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        place.updatePlace(dto);
        return place;
    }

    @Transactional
    public String deletePlace(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if(place.isPresent()){
            Place del = place.get();
            del.setDeleted(true);
            placeRepository.save(del);
        }
        else throw new PlaceNotFoundException();
        return "Success";
    }

    @Transactional
    public int updateView(Long id) {
        return placeRepository.updateView(id);
    }
}
