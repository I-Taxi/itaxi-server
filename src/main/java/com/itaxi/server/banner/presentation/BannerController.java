package com.itaxi.server.banner.presentation;

import com.itaxi.server.banner.application.BannerService;
import com.itaxi.server.banner.application.dto.BannerCreateDto;
import com.itaxi.server.banner.application.dto.BannerUpdateDto;
import com.itaxi.server.banner.presentation.reponse.*;
import com.itaxi.server.banner.presentation.request.BannerCreateRequest;
import com.itaxi.server.banner.presentation.request.BannerUpdateRequest;
import com.itaxi.server.docs.ApiDoc;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    @Transactional
    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PostMapping
    public ResponseEntity<BannerCreateResponse> createBanner(@RequestBody BannerCreateRequest request) {
        BannerCreateResponse bannerCreateResponse = bannerService.createBanner(BannerCreateDto.from(request));

        return ResponseEntity.ok(bannerCreateResponse);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.CREATE_NOTICE)
    @PutMapping("{bannerId}")
    public ResponseEntity<BannerUpdateResponse> updateBanner(@PathVariable Long bannerId, @RequestBody BannerUpdateRequest request){

        BannerUpdateResponse bannerUpdateResponse = bannerService.updateBanner(bannerId,BannerUpdateDto.from(request));
        return ResponseEntity.ok(bannerUpdateResponse);
    }

    @ApiOperation(value = ApiDoc.READ_NOTICE)
    @GetMapping("/{bannerId}")
    public ResponseEntity<BannerReadResponse> readBanner(@PathVariable Long bannerId){
        BannerReadResponse bannerReadResponse = bannerService.readBanner(bannerId);
        return ResponseEntity.ok(bannerReadResponse);
    }

    @ApiOperation(value = ApiDoc.READ_ALL_NOTICES)
    @GetMapping
    public ResponseEntity<List> readAllBanner() {
        List<BannerReadAllResponse> result = bannerService.readAllBanners();

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.READ_ALL_NOTICES)
    @GetMapping("/local-datetime")
    public ResponseEntity<List> readAllRecentBanner(@RequestParam@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) final LocalDateTime time) {
        List<BannerReadAllRecentResponse> result = bannerService.readAllRecentBanners(time);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.DELETE_NOTICE)
    @DeleteMapping("/{bannerId}")
    public ResponseEntity<String> deleteBanner(@PathVariable Long bannerId){
        String result = bannerService.deleteBanner(bannerId);
        return ResponseEntity.ok(result);
    }
}
