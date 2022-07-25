package com.itaxi.server.notice.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.notice.application.NoticeService;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.application.dto.NoticeUpdateDto;
import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import com.itaxi.server.notice.presentation.request.NoticeUpdateRequest;
import com.itaxi.server.notice.presentation.response.NoticeReadAllResponse;
import com.itaxi.server.notice.presentation.response.NoticeReadResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PostMapping
    public ResponseEntity<Long> createNotice(@RequestBody NoticeCreateRequest request) {
        Long id = noticeService.createNotice(NoticeCreateDto.from(request));

        return ResponseEntity.ok(id);
    }

    @ApiOperation(value = ApiDoc.READ_NOTICE)
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeReadResponse> readNotice(@PathVariable Long noticeId) {
        NoticeReadResponse response = noticeService.readNotice(noticeId);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = ApiDoc.READ_ALL_NOTICES)
    @GetMapping
    public ResponseEntity<List> readAllNotices() {
        List<NoticeReadAllResponse> result = noticeService.readAllNotices();

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.UPDATE_NOTICE)
    @PutMapping("/{noticeId}")
    public ResponseEntity<String> updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateRequest request) {
        String result = noticeService.updateNotice(noticeId, NoticeUpdateDto.from(request));

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.DELETE_NOTICE)
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) {
        String result = noticeService.deleteNotice(noticeId);

        return ResponseEntity.ok(result);
    }
}
