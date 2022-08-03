package com.itaxi.server.place.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.application.PlaceDto;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.place.domain.Place;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @ApiOperation(value = ApiDoc.PLACE_READ)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Place> findAllPlace() {
        return placeService.findAll();
    }

    @ApiOperation(value = ApiDoc.PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PlaceDto.Res create(@RequestBody final PlaceDto.AddPlaceReq dto) {
        return new PlaceDto.Res(placeService.create(dto));
    }
    @ApiOperation(value = ApiDoc.PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public int updateView(@PathVariable final long id, Model model) {
        //Res dto = placeService.findById(id);
        return placeService.updateView(id);
    }
    @ApiOperation(value = ApiDoc.PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public PlaceDto.Res update(@PathVariable final long id, @RequestBody final PlaceDto.UpdatePlaceReq dto) {
        return new PlaceDto.Res(placeService.updatePlace(id, dto));
    }

    @ApiOperation(value = ApiDoc.PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable final long id, @RequestBody final PlaceDto.DeletePlaceReq dto) {
        placeService.deletePlace(id, dto);
    }
}