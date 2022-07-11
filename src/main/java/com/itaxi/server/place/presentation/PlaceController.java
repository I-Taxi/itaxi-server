package com.itaxi.server.place.presentation;

import com.itaxi.server.place.application.PlaceDto;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@RestController
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    //@GetMapping("/")
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Place> findAllPlace() {
        return placeRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Place> create(@RequestBody Place place) {
        Place savedPlace = placeRepository.save(place);
        return ResponseEntity.ok(savedPlace);
    }
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public int updateView(@PathVariable final long id, Model model) {
        //Res dto = placeService.findById(id);
        return placeService.updateView(id);
    }
    @RequestMapping(value = "/deleted", method = RequestMethod.GET)
    public Iterable<Place> findAllPlaceNotDeleted() {
        return placeRepository.findByDeleted();
    }

    @RequestMapping(value = "/dto", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PlaceDto.Res create(@RequestBody final PlaceDto.AddPlaceReq dto) {
        return new PlaceDto.Res(placeService.create(dto));
    }

    @RequestMapping(value = "/dto/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public PlaceDto.Res update(@PathVariable final long id, @RequestBody final PlaceDto.UpdatePlaceReq dto) {
        return new PlaceDto.Res(placeService.updatePlace(id, dto));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable final long id, @RequestBody final PlaceDto.DeletePlaceReq dto) {
        placeService.deletePlace(id, dto);
    }
}