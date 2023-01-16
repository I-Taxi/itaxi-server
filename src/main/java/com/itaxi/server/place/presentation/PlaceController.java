package com.itaxi.server.place.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.place.application.dto.AddPlaceDto;
import com.itaxi.server.place.application.dto.DeletePlaceDto;
import com.itaxi.server.place.application.dto.ResDto;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.presentation.reponse.PlaceFindResponse;
import com.itaxi.server.place.presentation.request.PlaceDeleteRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;


    @ApiOperation(value = ApiDoc.PLACE_READ )
    @RequestMapping(value = "/{findType}",method = RequestMethod.GET)
    public ResponseEntity<List> ReadAll(@PathVariable int findType) {
        return ResponseEntity.ok(placeService.ReadAll(findType));
    }

    @ApiOperation(value = ApiDoc.PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResDto create(@RequestBody final AddPlaceDto dto) {return new ResDto(placeService.create(dto));}

    @ApiOperation(value = ApiDoc.PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public int updateView(@PathVariable final long id, Model model) {
        //Res dto = placeService.findById(id);
        return placeService.updateView(id);
    }
    @ApiOperation(value = ApiDoc.PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResDto update(@PathVariable final long id, @RequestBody final UpdatePlaceDto dto) {
        return new ResDto(placeService.updatePlace(id, dto));
    }

    @ApiOperation(value = ApiDoc.PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable final long id, @RequestBody final PlaceDeleteRequest dto) {
        return placeService.deletePlace(id, dto.getUid());
    }


}