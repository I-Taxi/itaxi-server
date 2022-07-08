package com.itaxi.server.notice.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.notice.application.NoticeService;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PostMapping
    // reponse entity써야 httpstatus와 body 같은거 넣기 좋음
    public ResponseEntity<Long> createNotice(@RequestBody NoticeCreateRequest request) {
        // create 함수로 noticecreateDto 보내기
        Long id = noticeService.createNotice(NoticeCreateDto.from(request));

        return ResponseEntity.ok(id);
    }

    @ApiOperation(value = ApiDoc.DELETE_NOTICE)
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) {
        String result = noticeService.deleteNotice(noticeId);

        return ResponseEntity.ok(result);
    }
}
