package com.itaxi.server.banner.presentation;

import com.itaxi.server.banner.application.BannerService;
import com.itaxi.server.banner.application.dto.*;
import com.itaxi.server.banner.presentation.reponse.*;
import com.itaxi.server.banner.presentation.request.*;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.banner.BannerBadParamException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BannerCreateResponse> createBanner(@RequestBody BannerCreateRequest request) {
        BannerCreateResponse bannerCreateResponse = bannerService.createBanner(BannerCreateDto.from(request));

        return ResponseEntity.ok(bannerCreateResponse);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_UPDATE)
    @RequestMapping(value = "/{bannerId}",method = RequestMethod.PUT)
    public ResponseEntity<BannerUpdateResponse> updateBanner(@PathVariable Long bannerId, @RequestBody BannerUpdateRequest request){
        BannerUpdateResponse bannerUpdateResponse = bannerService.updateBanner(bannerId,BannerUpdateDto.from(request));
        return ResponseEntity.ok(bannerUpdateResponse);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ)
    @RequestMapping(value = "/{bannerId}",method = RequestMethod.GET)
    public ResponseEntity<BannerReadResponse> readBanner(@PathVariable Long bannerId){
        BannerReadResponse bannerReadResponse = bannerService.readBanner(bannerId);
        return ResponseEntity.ok(bannerReadResponse);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ_ALL)
    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public ResponseEntity<List> readAllBanner(@RequestBody BannerReadAllRequest request) {
        List<BannerReadAllResponse> result = bannerService.readAllBanners(request.getUid());

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = ApiDoc.BANNER_READ_RECENT_ALL)
    @RequestMapping(value = "/recent",method = RequestMethod.GET)
    public ResponseEntity<List> readAllRecentBanner(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) final LocalDateTime time) {
        if(time==null) throw new BannerBadParamException();
        List<BannerReadAllRecentResponse> result = bannerService.readAllRecentBanners(time);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @ApiOperation(value = ApiDoc.BANNER_DELETE)
    @RequestMapping(value = "/delete/{bannerId}/",method = RequestMethod.PUT)
    public ResponseEntity<String> deleteBanner(@PathVariable Long bannerId, @RequestBody BannerDeleteRequest request){
        String result = bannerService.deleteBanner(bannerId,BannerDeleteDto.from(request));
        return ResponseEntity.ok(result);
    }
}
