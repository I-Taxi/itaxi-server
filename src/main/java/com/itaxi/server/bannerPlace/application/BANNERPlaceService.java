package com.itaxi.server.bannerPlace.application;

import com.itaxi.server.bannerPlace.application.dto.AddBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.DeleteBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.UpdateBANNERPlaceDto;
import com.itaxi.server.bannerPlace.domain.BANNERPlace;
import com.itaxi.server.bannerPlace.domain.repository.BANNERPlaceRepository;
import com.itaxi.server.bannerPlace.presentation.response.BANNERPlaceResponse;
import com.itaxi.server.exception.banner.BannerNotFoundException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceCntMinusException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotFoundException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotNullException;
import com.itaxi.server.exception.notice.NoticeNotFoundException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.ktxPlace.application.dto.DeleteKTXPlaceDto;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BANNERPlaceService {
    private final BANNERPlaceRepository bannerPlaceRepository;

    @Transactional
    public BANNERPlace create(AddBANNERPlaceDto dto) {
        if(dto.getCnt()<0) throw new BannerPlaceCntMinusException(HttpStatus.INTERNAL_SERVER_ERROR);
        if(dto.getName()==null || dto.getName()==""|| dto.getName().equals(" ")) throw new BannerPlaceNotNullException(HttpStatus.INTERNAL_SERVER_ERROR);
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
        if(dto.getName()==null || dto.getName()==""|| dto.getName().equals(" ")) throw new BannerPlaceNotNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        final BANNERPlace bannerPlace = bannerPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        bannerPlace.updateBANNERPlace(dto);
        return bannerPlace;
    }

    public String deleteBANNERPlace(Long id) {
        Optional<BANNERPlace> banner = bannerPlaceRepository.findById(id);
        if(banner.isPresent()){
            BANNERPlace del = banner.get();
            del.setDeleted(true);
            bannerPlaceRepository.save(del);
        }
        else throw new BannerPlaceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        return "Success";
    }

    @Transactional
    public Long updateView(Long id) {
        BANNERPlace bannercnt = null;
        Optional<BANNERPlace> bannerInfo = bannerPlaceRepository.findById(id);

        if(bannerInfo.isPresent()){
            if(bannerInfo.get().getCnt()>0) {
                bannercnt = bannerInfo.get();
                bannercnt.setCnt(bannercnt.getCnt() + 1);
                bannerPlaceRepository.save(bannercnt);
            }
            else throw new BannerPlaceCntMinusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            throw new BannerPlaceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return bannercnt.getCnt();
    }




}
