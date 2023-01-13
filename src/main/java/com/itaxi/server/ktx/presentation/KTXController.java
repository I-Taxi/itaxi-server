package com.itaxi.server.ktx.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.ktx.BadDateException;
import com.itaxi.server.exception.ktx.KTXRequestBodyNullException;
import com.itaxi.server.exception.ktx.SamePlaceException;
import com.itaxi.server.exception.ktx.WrongCapacityException;
import com.itaxi.server.ktx.application.KTXService;
import com.itaxi.server.ktx.application.dto.*;
import com.itaxi.server.ktxPlace.application.KTXPlaceService;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
        return ResponseEntity.ok(ktxService.getPostLog(memberUidDTO.getUid()));
    }

    @ApiOperation(value = ApiDoc.KTX_HISTORY_DETAIL)
    @GetMapping(value = "history/{ktxId}")
    public ResponseEntity<KTXLogDetail> getKTXLogDetail(@PathVariable long ktxId) {
        return ResponseEntity.ok(ktxService.getKTXLogDetail(ktxId));
    }

    @ApiOperation(value = ApiDoc.KTX_READ)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<KTXGetResDto>> getKTXDto(@RequestParam(value = "depId", required = false)final Long depId, @RequestParam(value = "dstId", required = false)final Long dstId, @RequestParam(value = "time")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)final LocalDate time) {
        return ResponseEntity.ok(ktxService.getKTX(depId, dstId, time));
    }

    @ApiOperation(value = ApiDoc.KTX_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<KTXInfoResponse> create(@Valid @RequestBody(required = false) final AddKTXDto dto) {
        // RequestBody가 null일 때
        if (dto == null) throw new KTXRequestBodyNullException(HttpStatus.INTERNAL_SERVER_ERROR);
        // 출발과 도착 장소가 같을 때
        if (dto.getDstId() == dto.getDepId()) throw new SamePlaceException(HttpStatus.INTERNAL_SERVER_ERROR);
        // capacity 1~10 이 아닐 때
        if (dto.getCapacity() > 10 || dto.getCapacity() < 1) throw new WrongCapacityException(HttpStatus.INTERNAL_SERVER_ERROR);
        // 날짜 3달 후까지로
        Period period = getPeriod(LocalDateTime.now(), dto.getDeptTime());
        if (period.getYears() >= 1 || period.getMonths() >= 3) throw new BadDateException(HttpStatus.INTERNAL_SERVER_ERROR);

        KTXInfoResponse response = ktxService.createKTX(dto);
        ktxPlaceService.updateView(dto.getDepId());
        ktxPlaceService.updateView(dto.getDstId());

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

    // 두 날짜 사이의 차이 구하기
    private static Period getPeriod(LocalDateTime a, LocalDateTime b) {
        return Period.between(a.toLocalDate(), b.toLocalDate());
    }
}
