package com.itaxi.server.advertisement.application;

import com.itaxi.server.advertisement.application.dto.AdvertisementUploadResponse;
import com.itaxi.server.advertisement.domain.Advertisement;
import com.itaxi.server.advertisement.domain.repository.AdvertisementRepository;
import com.itaxi.server.config.FilePathConfig;
import com.itaxi.server.exception.advertisement.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    @Autowired
    private final AdvertisementRepository advertisementRepository;

    private String FILE_PATH_ROOT = FilePathConfig.FILE_PATH_ROOT;

//    @Transactional
//    public AdvertisementUploadResponse createAdvertisement(MultipartFile file) throws IOException {
//        advertisementRepository.save(Advertisement.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .image(compressImage(file.getBytes())).build());
//
//        return new AdvertisementUploadResponse(file.getOriginalFilename(), file.getContentType());
//    }
//
//    @Transactional
//    public Advertisement getAdvertisementInfo(String name) {
//        Optional<Advertisement> dbImage = advertisementRepository.findByName(name);
//
//        return Advertisement.builder()
//                .name(dbImage.get().getName())
//                .type(dbImage.get().getType())
//                .image(decompressImage(dbImage.get().getImage())).build();
//
//    }

    @Transactional
    public byte[] getAdvertisement(String name) {
        byte[] image;

        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+name));
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }

        return image;
    }

    @Transactional
    public List<String> getAllAdvertisement(){
        List<String> imageNames = new ArrayList<String>();
        File filePath = new File(FILE_PATH_ROOT);
        File[] files = filePath.listFiles();

        for (File file : files) {
            imageNames.add(file.getName());
        }

        return imageNames;
    }


    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }
}
