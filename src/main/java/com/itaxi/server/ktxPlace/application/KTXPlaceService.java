package com.itaxi.server.ktxPlace.application;

import com.itaxi.server.cheaker.AdminChecker;
import com.itaxi.server.exception.ktx.KTXBadCntException;
import com.itaxi.server.exception.ktx.KTXNameEmptyException;
import com.itaxi.server.exception.member.MemberNotAdminException;
import com.itaxi.server.exception.place.PlaceNameDuplicationException;
import com.itaxi.server.ktxPlace.application.dto.AddKTXPlaceDto;
import com.itaxi.server.ktxPlace.application.dto.KTXPlaceResponse;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktxPlace.domain.repository.KTXPlaceRepository;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.place.application.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KTXPlaceService {
    private final KTXPlaceRepository ktxPlaceRepository;
    private final AdminChecker adminChecker;
    private final PlaceService placeService;
    @Transactional
    public KTXPlace create(AddKTXPlaceDto dto) {
        if (dto.getName().equals(null) || dto.getName().equals("") || dto.getName().equals(" ")) throw new KTXNameEmptyException();
        if (dto.getCnt() < 0) throw new KTXBadCntException();

        KTXPlace savedPlace = null;

        if (adminChecker.isAdmin(dto.getUid())) {
            Optional<KTXPlace> ktx = ktxPlaceRepository.findByName(dto.getName());
            if(ktx.isPresent()) throw new PlaceNameDuplicationException();
            dto.setName(placeService.XSSFiltering(dto.getName()));
            KTXPlace ktxPlace = new KTXPlace(dto.getName(),dto.getCnt());
            savedPlace = ktxPlaceRepository.save(ktxPlace);
        } else {
            throw new MemberNotAdminException();
        }

        return savedPlace;
    }

    @Transactional
    public List<KTXPlaceResponse> findAll() {
        List<KTXPlace> ktxPlaceList = ktxPlaceRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"));
        List<KTXPlaceResponse> ktxPlaceResponses = ktxPlaceList.stream().map(m -> new KTXPlaceResponse(m.getId(), m.getName(), m.getCnt())).collect(Collectors.toList());
        return ktxPlaceResponses;
    }

    @Transactional
    public KTXPlace updateKTXPlace(long id, UpdateKTXPlaceDto dto) {
        KTXPlace ktxPlace = ktxPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        if (adminChecker.isAdmin(dto.getUid())) {
            Optional<KTXPlace> ktx = ktxPlaceRepository.findByName(dto.getName());
            if(ktx.isPresent()) throw new PlaceNameDuplicationException();
            ktxPlace.setName(dto.getName());
            ktxPlaceRepository.save(ktxPlace);
        } else {
            throw new MemberNotAdminException();
        }

        return ktxPlace;
    }

    @Transactional
    public String deleteKTXPlace(Long id, String uid) {
        final KTXPlace ktxPlace = ktxPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);

        if (adminChecker.isAdmin(uid)) {
            ktxPlace.setDeleted(true);
            ktxPlaceRepository.save(ktxPlace);
        } else {
            throw new MemberNotAdminException();
        }

        return "Success";
    }

    @Transactional
    public Long updateView(Long id) {
        Long count = 0L;
        KTXPlace ktxPlace = ktxPlaceRepository.findById(id).orElseThrow(PlaceNotFoundException::new);

        count = ktxPlace.getCnt() + 1;
        ktxPlace.setCnt(count);

        ktxPlaceRepository.save(ktxPlace);

        return count;
    }

}
