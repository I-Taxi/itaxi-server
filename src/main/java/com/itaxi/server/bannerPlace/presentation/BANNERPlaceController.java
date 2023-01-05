package com.itaxi.server.bannerPlace.presentation;

import com.itaxi.server.bannerPlace.application.BANNERPlaceService;
import com.itaxi.server.bannerPlace.application.dto.AddBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.BANNERResDto;
import com.itaxi.server.bannerPlace.application.dto.DeleteBANNERPlaceDto;
import com.itaxi.server.bannerPlace.application.dto.UpdateBANNERPlaceDto;
import com.itaxi.server.bannerPlace.presentation.response.BANNERPlaceResponse;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.ktxPlace.application.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/banner-place")
public class BANNERPlaceController {
    private final BANNERPlaceService bannerPlaceService;

    @ApiOperation(value = ApiDoc.BANNER_PLACE_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<BANNERPlaceResponse>> findAllPlace() {
        return ResponseEntity.ok(bannerPlaceService.findAll());
    }

    @ApiOperation(value = ApiDoc.BANNER_PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<BANNERResDto> create(@RequestBody final AddBANNERPlaceDto dto) {
        return ResponseEntity.ok(new BANNERResDto(bannerPlaceService.create(dto)));
    }

    @ApiOperation(value = ApiDoc.BANNER_PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateView(@PathVariable final long id) {
        return ResponseEntity.ok(bannerPlaceService.updateView(id));
    }

    @ApiOperation(value = ApiDoc.BANNER_PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<BANNERResDto> update(@PathVariable final long id, @RequestBody final UpdateBANNERPlaceDto dto) {
        return ResponseEntity.ok(new BANNERResDto(bannerPlaceService.updateBANNERPlace(id, dto)));
    }

    @ApiOperation(value = ApiDoc.BANNER_PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable final long id, @RequestBody final DeleteBANNERPlaceDto dto) {
        bannerPlaceService.deleteBANNERPlace(id, dto);
    }
}
