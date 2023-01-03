package com.itaxi.server.ktxPlace.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.ktxPlace.application.dto.*;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ktx-place")
public class KTXPlaceController {
    private final KTXPlaceService ktxPlaceService;

    @ApiOperation(value = ApiDoc.KTX_PLACE_READ)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<KTXPlaceResponse> findAllPlace() {
        return ktxPlaceService.findAll();
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public KTXResDto create(@RequestBody final AddKTXPlaceDto dto) {
        return new KTXResDto(ktxPlaceService.create(dto));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public int updateView(@PathVariable final long id) {
        return ktxPlaceService.updateView(id);
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public KTXResDto update(@PathVariable final long id, @RequestBody final UpdateKTXPlaceDto dto) {
        return new KTXResDto(ktxPlaceService.updateKTXPlace(id, dto));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable final long id, @RequestBody final DeleteKTXPlaceDto dto) {
        ktxPlaceService.deleteKTXPlace(id, dto);
    }
}
