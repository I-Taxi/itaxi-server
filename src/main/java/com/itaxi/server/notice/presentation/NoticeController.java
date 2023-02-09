package com.itaxi.server.notice.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.notice.application.NoticeService;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.application.dto.NoticeUpdateDto;
import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import com.itaxi.server.notice.presentation.request.NoticeDeleteRequest;
import com.itaxi.server.notice.presentation.request.NoticeUpdateRequest;
import com.itaxi.server.notice.presentation.response.NoticeReadAllResponse;
import com.itaxi.server.notice.presentation.response.NoticeReadResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @Transactional
    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PostMapping
    public ResponseEntity<Long> createNotice(@RequestBody NoticeCreateRequest request) {

        Long id = noticeService.createNotice(NoticeCreateDto.from(request), request.getUid());

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

    @Transactional
    @ApiOperation(value = ApiDoc.UPDATE_NOTICE)
    @PutMapping("/{noticeId}")
    public ResponseEntity<String> updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateRequest request) {
        String result = noticeService.updateNotice(noticeId, NoticeUpdateDto.from(request), request.getUid());

        return ResponseEntity.ok(result);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.DELETE_NOTICE)
    @PutMapping("/delete/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId, @RequestBody NoticeDeleteRequest request) {
        String result = noticeService.deleteNotice(noticeId, request.getUid());

        return ResponseEntity.ok(result);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.UPDATE_NOTICE_VIEWCNT)
    @PutMapping("/{noticeId}/view")
    public ResponseEntity<Long> updateNoticeViewCnt(@PathVariable Long noticeId) {
        Long result = noticeService.updateNoticeViewCnt(noticeId);

        return ResponseEntity.ok(result);
    }
}
