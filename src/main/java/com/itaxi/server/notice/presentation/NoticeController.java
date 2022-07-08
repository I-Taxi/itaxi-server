package com.itaxi.server.notice.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.notice.application.NoticeService;
import com.itaxi.server.notice.application.dto.NoticeCreateDto;
import com.itaxi.server.notice.presentation.request.NoticeCreateRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PostMapping
    // reponse entity인 create 생성
    public ResponseEntity<Long> create(@RequestBody NoticeCreateRequest request) {
        // create 함수로 noticecreateDto 보내기
        Long id = noticeService.create(NoticeCreateDto.from(request));
        return ResponseEntity.ok(id);
    }
}
