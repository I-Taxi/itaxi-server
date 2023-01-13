package com.itaxi.server.member.presentation;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.member.application.FavorJoinerService;
import com.itaxi.server.member.application.dto.FavorJoinerCreateDto;
import com.itaxi.server.member.application.dto.FavorJoinerInfo;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import com.itaxi.server.member.domain.FavorJoiner;
import com.itaxi.server.member.presentation.response.FavorJoinerReadAllResponse;
import com.itaxi.server.notice.presentation.response.NoticeReadAllResponse;
import com.itaxi.server.place.domain.Place;
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
    @PostMapping(value = "/create")
    public String create(@RequestBody FavorJoinerCreateDto favorJoinerCreateDto) {
        favorJoinerService.create(favorJoinerCreateDto);
        return "Success";
    }

    @ApiOperation(value = ApiDoc.FAVORITE_JOINER_READ_ALL_BY_MEMBER)
    @GetMapping(value = "/create/{uid}")
    public ResponseEntity<List> readAllFavorByMember(String uid) {
        return ResponseEntity.ok(favorJoinerService.readAllFavorByMember(uid));

    }


    @ApiOperation(value = ApiDoc.FAVORITE_JOINER_DELETE)
    @PatchMapping(value = "/delete/{favorId}")
    public String delete(@PathVariable Long favorId ) {
        return favorJoinerService.delete(favorId);
    }


}
