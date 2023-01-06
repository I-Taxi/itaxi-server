package com.itaxi.server.bannerPlace.application;

import com.itaxi.server.bannerPlace.application.dto.AddBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.DeleteBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.UpdateBANNERPlaceDto;
import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import com.itaxi.server.bannerPlace.domain.repository.BANNERPlaceRepository;
import com.itaxi.server.bannerPlace.presentation.response.BANNERPlaceResponse;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.ktxPlace.application.dto.DeleteKTXPlaceDto;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BANNERPlaceService {
    private final BANNERPlaceRepository bannerPlaceRepository;

    @Transactional
    public BANNERPlace create(AddBANNERPlaceDto dto) {
        return bannerPlaceRepository.save(dto.toEntity());
    }

    @Transactional
    public List<BANNERPlaceResponse> findAll() {
        List<BANNERPlace> bannerPlaceList = bannerPlaceRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"));
        List<BANNERPlaceResponse> bannerPlaceResponses = bannerPlaceList.stream().map(m -> new BANNERPlaceResponse(m.getId(), m.getName(), m.getCnt())).collect(Collectors.toList());
        return bannerPlaceResponses;
    }

    @Transactional
    public BANNERPlace updateBANNERPlace(long id, UpdateBANNERPlaceDto dto) {
        final BANNERPlace bannerPlace = bannerPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        bannerPlace.updateBANNERPlace(dto);
        return bannerPlace;
    }

    public BANNERPlace deleteBANNERPlace(Long id, DeleteBANNERPlaceDto dto) {
        final BANNERPlace bannerPlace = bannerPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        bannerPlace.deleteBANNERPlace(dto);
        return bannerPlace;
    }

    @Transactional
    public int updateView(Long id) {
        return bannerPlaceRepository.updateView(id);
    }




}
