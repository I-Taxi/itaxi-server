package com.itaxi.server.advertisement.presentation;

import com.itaxi.server.advertisement.application.AdvertisementService;
import com.itaxi.server.advertisement.presentation.request.AdCreateRequest;
import com.itaxi.server.advertisement.presentation.request.AdGetImageRequest;
import com.itaxi.server.advertisement.presentation.response.AdGetAllResponse;
import com.itaxi.server.advertisement.presentation.response.AdGetImageResponse;
import com.itaxi.server.advertisement.presentation.response.AdGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @PostMapping()
    public ResponseEntity<String> createAdvertisement(@RequestBody AdCreateRequest request){
        return ResponseEntity.ok(advertisementService.createAdvertisement(request));
    }

    @GetMapping("/{imgName}")
    public ResponseEntity<AdGetResponse> getAdvertisement(@PathVariable String imgName){
        return ResponseEntity.ok(advertisementService.getAdvertisement(imgName));
    }

    @PostMapping("/{name}")
    public ResponseEntity<AdGetImageResponse> getAdvertisementImage(@RequestBody AdGetImageRequest request){


        return ResponseEntity.ok(advertisementService.getAdvertisementImage(request.getImgName()));
    }

    @GetMapping
    public ResponseEntity<List<AdGetAllResponse>> getAllAdvertisement() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisement());
    }

    @DeleteMapping("/{imgId}")
    public ResponseEntity<String> deleteAdvertisement(@PathVariable Long imgId){
        return ResponseEntity.ok(advertisementService.deleteAdvertisement(imgId));
    }
}
