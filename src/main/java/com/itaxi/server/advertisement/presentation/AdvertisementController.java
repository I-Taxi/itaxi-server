package com.itaxi.server.advertisement.presentation;

import com.itaxi.server.advertisement.application.AdvertisementService;
import com.itaxi.server.advertisement.presentation.request.AdCreateRequest;
import com.itaxi.server.advertisement.presentation.request.AdGetImageRequest;
import com.itaxi.server.advertisement.presentation.response.AdGetAllResponse;
import com.itaxi.server.advertisement.presentation.response.AdGetResponse;
import com.itaxi.server.config.FilePathConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private String FILE_PATH_ROOT = FilePathConfig.FILE_PATH_ROOT;

    @PostMapping()
    public ResponseEntity<String> createAdvertisement(@RequestBody AdCreateRequest request){
        return ResponseEntity.ok(advertisementService.createAdvertisement(request));
    }

    @GetMapping("/{imgName}")
    public ResponseEntity<AdGetResponse> getAdvertisement(@PathVariable String imgName){
        return ResponseEntity.ok(advertisementService.getAdvertisement(imgName));
    }

    @PostMapping("/{name}")
    public ResponseEntity<byte[]> getAdvertisementImage(@RequestBody AdGetImageRequest request){
        byte[] image = advertisementService.getAdvertisementImage(request.getImgName());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
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
