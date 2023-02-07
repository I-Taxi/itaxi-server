package com.itaxi.server.history.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.history.application.HistoryService;
import com.itaxi.server.history.application.dto.HistoryLog;
import com.itaxi.server.history.application.dto.HistoryLogDetail;
import com.itaxi.server.member.application.dto.MemberUidDTO;
import com.itaxi.server.post.presentation.request.PostGetLogDetailRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @ApiOperation(value = ApiDoc.POST_HISTORY)
    @PostMapping
    public ResponseEntity<List<HistoryLog>> getPostLog(@RequestBody MemberUidDTO memberUidDTO) {
        return ResponseEntity.ok(historyService.getPostLog(memberUidDTO.getUid()));
    }

    @ApiOperation(value = ApiDoc.POST_HISTORY_DETAIL)
    @PostMapping(value = "history/{Id}")
    public ResponseEntity<HistoryLogDetail> getLogDetail(@RequestParam(value = "type")final Long type, @PathVariable long Id, @RequestBody PostGetLogDetailRequest request) {
        return ResponseEntity.ok(historyService.getLogDetail(type,Id,request));
    }
}
