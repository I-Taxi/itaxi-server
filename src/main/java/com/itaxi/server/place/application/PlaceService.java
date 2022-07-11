package com.itaxi.server.place.application;

import com.itaxi.server.place.domain.repository.PlaceRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Place create(PlaceDto.AddPlaceReq dto) {
        return placeRepository.save(dto.toEntity());
    }
    @Transactional(readOnly = true)
    public Place findById(long id) {
        final Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        if (place == null)
            throw new PlaceNotFoundException();
        return place;
    }
    @Transactional
    public Place updatePlace(long id, PlaceDto.UpdatePlaceReq dto) {
        final Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        place.updatePlace(dto);
        return place;
    }

    @Transactional
    public Place deletePlace(Long id, PlaceDto.DeletePlaceReq dto) {
        final Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);;
        //final BaseEntity base = findById(id);
        place.deletePlace(dto);
        return place;
    }

    @Transactional
    public int updateView(Long id) {
        return placeRepository.updateView(id);
    }
}
