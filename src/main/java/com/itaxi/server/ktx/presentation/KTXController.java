package com.itaxi.server.ktx.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.ktx.application.KTXService;
import com.itaxi.server.ktx.application.dto.*;
import com.itaxi.server.ktx.presentation.request.KTXGetDetailRequest;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.post.application.dto.PostLogDetail;
import com.itaxi.server.post.presentation.request.PostGetLogDetailRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ktx")
public class KTXController {
    private final KTXService ktxService;
    private final KTXPlaceService ktxPlaceService;

    @ApiOperation(value = ApiDoc.KTX_HISTORY)
    @PostMapping(value = "history")
    public ResponseEntity<List<KTXLog>> getKTXLog(@RequestBody MemberUidDTO memberUidDTO) {
        return ResponseEntity.ok(ktxService.getKTXLog(memberUidDTO.getUid()));
    }

    @ApiOperation(value = ApiDoc.KTX_HISTORY_DETAIL)
    @PostMapping(value = "history/{ktxId}")
    public ResponseEntity<KTXLogDetail> getKTXLogDetail(@PathVariable long ktxId, @RequestBody KTXGetDetailRequest request) {
        return ResponseEntity.ok(ktxService.getKTXLogDetail(ktxId,request.getUid()));
    }

    @ApiOperation(value = ApiDoc.KTX_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<KTXGetResDto>> getKTXDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)final LocalDate time) {
        return ResponseEntity.ok(ktxService.getKTX(depId, dstId, time));
    }

    @ApiOperation(value = ApiDoc.KTX_GET_SINGLE)
    @PostMapping(value = "/{ktxId}")
    public ResponseEntity<KTXLogDetail> getSinglePost(@PathVariable long ktxId, @RequestBody PostGetLogDetailRequest request) {
        return ResponseEntity.ok(ktxService.getSingleKTXPost(ktxId,request));
    }

    @ApiOperation(value = ApiDoc.KTX_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<KTXInfoResponse> create(@Valid @RequestBody(required = false) final AddKTXDto dto) {
        KTXInfoResponse response = ktxService.createKTX(dto);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = ApiDoc.JOIN_KTX)
    @PostMapping("/{ktxId}/join")
    public ResponseEntity<KTXInfoResponse> joinKTX(@PathVariable Long ktxId, @RequestBody KTXJoinRequest request) {
        KTXInfoResponse result = ktxService.joinKTX(ktxId, KTXJoinDto.from(request, false));
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.EXIT_KTX)
    @PutMapping("/{ktxId}/join")
    public ResponseEntity<Long> exitKTX(@PathVariable Long ktxId, @RequestBody KTXExitRequest request) {
        Member result = ktxService.exitKTX(ktxId, request.getUid());
        return ResponseEntity.ok(result.getId());
    }

    @ApiOperation(value = ApiDoc.KTX_STOP)
    @PutMapping("/{ktxId}/stop")
    public ResponseEntity<String> stopKTX(@PathVariable Long ktxId, @RequestBody MemberUidDTO memberUidDTO) {
        String result = ktxService.stopKTX(ktxId, memberUidDTO.getUid());
        return ResponseEntity.ok(result);
    }
}
