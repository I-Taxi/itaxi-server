package com.itaxi.server.advertisement.application;

import com.itaxi.server.advertisement.application.dto.AdvertisementUploadResponse;
import com.itaxi.server.advertisement.domain.Advertisement;
import com.itaxi.server.advertisement.domain.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    @Autowired
    private final AdvertisementRepository advertisementRepository;

    @Transactional
    public AdvertisementUploadResponse createAdvertisement(MultipartFile file) throws IOException {
        advertisementRepository.save(Advertisement.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(compressImage(file.getBytes())).build());

        return new AdvertisementUploadResponse(file.getOriginalFilename(), file.getContentType());
    }

    @Transactional
    public Advertisement getAdvertisementInfo(String name) {
        Optional<Advertisement> dbImage = advertisementRepository.findByName(name);

        return Advertisement.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(decompressImage(dbImage.get().getImage())).build();

    }

    @Transactional
    public byte[] getAdvertisement(String name) {
        Optional<Advertisement> dbImage = advertisementRepository.findByName(name);
        byte[] image = decompressImage(dbImage.get().getImage());
        return image;
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
