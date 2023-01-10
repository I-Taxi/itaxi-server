package com.itaxi.server.banner.presentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.itaxi.server.banner.application.BannerService;
import com.itaxi.server.banner.application.dto.BannerCreateDto;
import com.itaxi.server.banner.application.dto.BannerUpdateDto;
import com.itaxi.server.banner.presentation.reponse.*;
import com.itaxi.server.banner.presentation.request.BannerCreateRequest;
import com.itaxi.server.banner.presentation.request.BannerUpdateRequest;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.banner.BannerRequestBodyException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotNullException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_CREATE)
    @PostMapping
    public ResponseEntity<BannerCreateResponse> createBanner(@Valid @RequestBody (required = false) BannerCreateRequest request) {

        if(request==null) throw new BannerRequestBodyException(HttpStatus.INTERNAL_SERVER_ERROR);
        BannerCreateResponse bannerCreateResponse = bannerService.createBanner(BannerCreateDto.from(request));

        return ResponseEntity.ok(bannerCreateResponse);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_UPDATE)
    @PutMapping("{bannerId}")
    public ResponseEntity<BannerUpdateResponse> updateBanner(@PathVariable Long bannerId, @RequestBody(required = false) BannerUpdateRequest request){
        if(request==null) throw new BannerRequestBodyException(HttpStatus.INTERNAL_SERVER_ERROR);
        BannerUpdateResponse bannerUpdateResponse = bannerService.updateBanner(bannerId,BannerUpdateDto.from(request));
        return ResponseEntity.ok(bannerUpdateResponse);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ)
    @GetMapping("/{bannerId}")
    public ResponseEntity<BannerReadResponse> readBanner(@PathVariable Long bannerId){
        BannerReadResponse bannerReadResponse = bannerService.readBanner(bannerId);
        return ResponseEntity.ok(bannerReadResponse);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ_ALL)
    @GetMapping
    public ResponseEntity<List> readAllBanner() {
        List<BannerReadAllResponse> result = bannerService.readAllBanners();

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ_RECENT_ALL)
    @GetMapping("/local-datetime")
    public ResponseEntity<List> readAllRecentBanner(@RequestParam(required = false)@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) final LocalDateTime time) {
        if(time==null) throw new BannerRequestBodyException(HttpStatus.INTERNAL_SERVER_ERROR);
        List<BannerReadAllRecentResponse> result = bannerService.readAllRecentBanners(time);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_DELETE)
    @DeleteMapping("/{bannerId}")
    public ResponseEntity<String> deleteBanner(@PathVariable Long bannerId){
        String result = bannerService.deleteBanner(bannerId);
        return ResponseEntity.ok(result);
    }
}
