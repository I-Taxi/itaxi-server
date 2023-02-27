package com.itaxi.server.ktxPlace.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.ktxPlace.application.dto.*;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.ktxPlace.presentation.request.DeleteKTXPlaceRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ktx-place")
public class KTXPlaceController {
    private final KTXPlaceService ktxPlaceService;

    @ApiOperation(value = ApiDoc.KTX_PLACE_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List> findAllPlace() {
        return ResponseEntity.ok(ktxPlaceService.findAll());
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<KTXResDto> create(@Valid @RequestBody final AddKTXPlaceDto dto) {
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
        return ResponseEntity.ok(new KTXResDto(ktxPlaceService.updateKTXPlace(id, dto)));
    }

    @ApiOperation(value = ApiDoc.KTX_PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable final long id, @RequestBody final DeleteKTXPlaceRequest dto) {
        String result = ktxPlaceService.deleteKTXPlace(id, dto.getUid());
        return ResponseEntity.ok(result);
    }
}
