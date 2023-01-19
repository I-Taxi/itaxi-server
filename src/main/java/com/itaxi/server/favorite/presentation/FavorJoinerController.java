package com.itaxi.server.favorite.presentation;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.favorite.application.FavorJoinerService;
import com.itaxi.server.favorite.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.favorite.presentation.request.FavorReadRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/favorite")
public class FavorJoinerController {
   private final FavorJoinerService favorJoinerService;

    @ApiOperation(value = ApiDoc.FAVORITE_JOINER_CREATE)
    @PostMapping
    public ResponseEntity<String> create(@RequestBody FavorJoinerCreateDto favorJoinerCreateDto) {
        favorJoinerService.create(favorJoinerCreateDto);
        return ResponseEntity.ok("Success");
    }

    @ApiOperation(value = ApiDoc.FAVORITE_JOINER_READ_ALL_BY_MEMBER)
    @PutMapping
    public ResponseEntity<List> readAllFavorByMember(@RequestBody FavorReadRequest request) {
        return ResponseEntity.ok(favorJoinerService.readAllFavorByMember(request.getUid()));

    }


    @ApiOperation(value = ApiDoc.FAVORITE_JOINER_DELETE)
    @PatchMapping(value = "/{favorId}")
    public String delete(@PathVariable Long favorId ) {
        return favorJoinerService.delete(favorId);
    }


}
