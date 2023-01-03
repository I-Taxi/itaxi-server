package com.itaxi.server.ktx.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.ktx.application.KTXService;
import com.itaxi.server.ktx.application.dto.*;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ktx")
public class KTXController {
    private final KTXService ktxService;
    private final KTXPlaceService ktxPlaceService;

    @ApiOperation(value = ApiDoc.KTX_HISTORY)
    @PostMapping(value = "history")
    public List<KTXLog> getKTXLog(@RequestBody MemberUidDTO memberUidDTO) {
        return ktxService.getPostLog(memberUidDTO.getUid());
    }

    @ApiOperation(value = ApiDoc.KTX_HISTORY_DETAIL)
    @GetMapping(value = "history/{ktxId}")
    public KTXLogDetail getKTXLogDetail(@PathVariable long ktxId) {
        return ktxService.getKTXLogDetail(ktxId);
    }

    @ApiOperation(value = ApiDoc.KTX_READ)
    @RequestMapping(method = RequestMethod.GET)
    public List<KTXGetResDto> getKTXDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)final LocalDate time) {
        return ktxService.getKTX(depId, dstId, time);
    }

    @ApiOperation(value = ApiDoc.KTX_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<KTXInfoResponse> create(@RequestBody final AddKTXDto dto) {
        KTXInfoResponse response = ktxService.createKTX(dto);
        ktxPlaceService.updateView(dto.getDepId());
        ktxPlaceService.updateView(dto.getDstId());

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = ApiDoc.JOIN_POST)
    @PostMapping("/{ktxId}/join")
    public ResponseEntity<KTXInfoResponse> joinKTX(@PathVariable Long ktxId, @RequestBody KTXJoinRequest request) {
        KTXInfoResponse result = ktxService.joinKTX(ktxId, KTXJoinDto.from(request, false));
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.EXIT_POST)
    @PutMapping("/{ktxId}/join")
    public ResponseEntity<String> exitKTX(@PathVariable Long ktxId, @RequestBody KTXExitRequest request) {
        String result = ktxService.exitKTX(ktxId, request.getUid());
        return ResponseEntity.ok(result);
    }

    // ktx 채팅방 모집 중단
    @ApiOperation(value = ApiDoc.KTX_STOP)
    @PutMapping("/{ktxId}/stop")
    public ResponseEntity<String> stopKTX(@PathVariable Long ktxId, @RequestBody MemberUidDTO memberUidDTO) {
        String result = ktxService.stopKTX(ktxId, memberUidDTO.getUid());
        return ResponseEntity.ok(result);
    }
}
