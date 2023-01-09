package com.itaxi.server.ktxPlace.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.ktx.BadCntException;
import com.itaxi.server.exception.ktx.NameNullException;
import com.itaxi.server.ktxPlace.application.dto.*;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ktx-place")
public class KTXPlaceController {
    private final KTXPlaceService ktxPlaceService;

    @ApiOperation(value = ApiDoc.KTX_PLACE_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<KTXPlaceResponse>> findAllPlace() {
        return ResponseEntity.ok(ktxPlaceService.findAll());
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<KTXResDto> create(@RequestBody final AddKTXPlaceDto dto) {
        // name 비었을 때
        if (dto.getName() == null) throw new NameNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        // cnt 마이너스로 올 때
        if (dto.getCnt() < 0) throw new BadCntException(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok(new KTXResDto(ktxPlaceService.create(dto)));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateView(@PathVariable final long id) {
        return ResponseEntity.ok(ktxPlaceService.updateView(id));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<KTXResDto> update(@PathVariable final long id, @RequestBody final UpdateKTXPlaceDto dto) {
        // viewcnt 리턴값
        return ResponseEntity.ok(new KTXResDto(ktxPlaceService.updateKTXPlace(id, dto)));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable final long id) {
        String result = ktxPlaceService.deleteKTXPlace(id);
        return result;
    }
}
