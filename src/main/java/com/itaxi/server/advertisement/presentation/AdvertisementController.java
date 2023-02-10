package com.itaxi.server.advertisement.presentation;

import com.itaxi.server.advertisement.application.AdvertisementService;
import com.itaxi.server.advertisement.application.dto.AdvertisementUploadResponse;
import com.itaxi.server.advertisement.domain.Advertisement;
import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.advertisement.ImageNotFoundException;
import com.itaxi.server.report.application.dto.AddReportDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import java.lang.Object;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private String FILE_PATH_ROOT = "/Users/david/Documents/server/src/main/java/com/itaxi/server/advertisement/images/";

    //@ApiOperation(value = ApiDoc.REPORT_CREATE)
    //@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public ResponseEntity<AdvertisementUploadResponse> createAdvertisement(@RequestPart(value = "file", required = false)MultipartFile file) throws IOException {
//        AdvertisementUploadResponse response = advertisementService.createAdvertisement(file);
//
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/info/{name}")
//    public ResponseEntity<Advertisement>  getAdvertisementInfo(@PathVariable("name") String name){
//        Advertisement image = advertisementService.getAdvertisementInfo(name);
//
//        return ResponseEntity.ok(image);
//    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getAdvertisement(@PathVariable("name") String name){
        byte[] image = advertisementService.getAdvertisement(name);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllAdvertisement() {
        List<String> imageNames = advertisementService.getAllAdvertisement();

        return ResponseEntity.ok(imageNames);
    }
}
