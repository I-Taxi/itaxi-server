package com.itaxi.server.ktxPlace.application;

import com.itaxi.server.ktxPlace.application.dto.AddKTXPlaceDto;
import com.itaxi.server.ktxPlace.application.dto.DeleteKTXPlaceDto;
import com.itaxi.server.ktxPlace.application.dto.KTXPlaceResponse;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktxPlace.domain.repository.KTXPlaceRepository;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KTXPlaceService {
    private final KTXPlaceRepository ktxPlaceRepository;

    @Transactional
    public KTXPlace create(AddKTXPlaceDto dto) {
        return ktxPlaceRepository.save(dto.toEntity());
    }

    @Transactional
    public List<KTXPlaceResponse> findAll() {
        List<KTXPlace> ktxPlaceList = ktxPlaceRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"));
        List<KTXPlaceResponse> ktxPlaceResponses = ktxPlaceList.stream().map(m -> new KTXPlaceResponse(m.getId(), m.getName(), m.getCnt())).collect(Collectors.toList());
        return ktxPlaceResponses;
    }

    @Transactional
    public KTXPlace updateKTXPlace(long id, UpdateKTXPlaceDto dto) {
        final KTXPlace ktxPlace = ktxPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        ktxPlace.updateKTXPlace(dto);
        return ktxPlace;
    }

    @Transactional
    public KTXPlace deleteKTXPlace(Long id, DeleteKTXPlaceDto dto) {
        final KTXPlace ktxPlace = ktxPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        ktxPlace.deleteKTXPlace(dto);
        return ktxPlace;
    }

    @Transactional
    public int updateView(Long id) {
        return ktxPlaceRepository.updateView(id);
    }

}
