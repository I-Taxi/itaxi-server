package com.itaxi.server.advertisement.presentation;

import com.itaxi.server.advertisement.application.AdvertisementService;
import com.itaxi.server.advertisement.application.dto.AdvertisementUploadResponse;
import com.itaxi.server.advertisement.domain.Advertisement;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.report.application.dto.AddReportDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    //@ApiOperation(value = ApiDoc.REPORT_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<AdvertisementUploadResponse> createAdvertisement(@RequestParam("image")MultipartFile file) throws IOException {
        AdvertisementUploadResponse response = advertisementService.createAdvertisement(file);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<Advertisement>  getAdvertisementInfo(@PathVariable("name") String name){
        Advertisement image = advertisementService.getAdvertisementInfo(name);

        return ResponseEntity.ok(image);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?>  getAdvertisement(@PathVariable("name") String name){
        byte[] image = advertisementService.getAdvertisement(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
