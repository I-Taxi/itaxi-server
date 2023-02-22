package com.itaxi.server.advertisement.application;

import com.itaxi.server.advertisement.application.dto.AdCreateDto;
import com.itaxi.server.advertisement.domain.Advertisement;
import com.itaxi.server.advertisement.domain.repository.AdvertisementRepository;
import com.itaxi.server.advertisement.presentation.request.AdCreateRequest;
import com.itaxi.server.advertisement.presentation.response.AdGetAllResponse;
import com.itaxi.server.advertisement.presentation.response.AdGetImageResponse;
import com.itaxi.server.advertisement.presentation.response.AdGetResponse;
import com.itaxi.server.config.FilePathConfig;
import com.itaxi.server.exception.advertisement.AdImageTypeNotProperException;
import com.itaxi.server.exception.advertisement.ImageNotFoundException;

import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdvertisementService {
    @Autowired
    private final AdvertisementRepository advertisementRepository;
    private String FILE_PATH_ROOT = FilePathConfig.FILE_PATH_ROOT;
    private ArrayList<String> types = new ArrayList<>(Arrays.asList("png", "jpg", "jpeg"));

    @Transactional
    public AdGetImageResponse getAdvertisementImage(String name) {
        byte[] image;
        Optional<Advertisement> advertisement = advertisementRepository.findByImgName(name);
        if(!advertisement.isPresent()) throw new ImageNotFoundException();

        String path = advertisement.get().getPath() + name + '.' + advertisement.get().getImgType();

        try {
            image = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }
        AdGetImageResponse result = new AdGetImageResponse(image,advertisement.get().getUrl());

        return result;
    }

    @Transactional
    public String createAdvertisement(AdCreateRequest request){
        boolean check = false;
        for(int i = 0; i<types.size(); i++){
            if(request.getImgType().equals(types.get(i))){
                check = true;
            }
        }
        if(check == false) throw new AdImageTypeNotProperException();

        AdCreateDto dto  = new AdCreateDto(request);
        Advertisement advertisement = new Advertisement(dto);
        advertisementRepository.save(advertisement);
        return "Success";
    }

    @Transactional
    public AdGetResponse getAdvertisement(String imgName) {
       Optional<Advertisement> advertisement = advertisementRepository.findByImgName(imgName);
        if(!advertisement.isPresent())throw new ImageNotFoundException();

        AdGetResponse adGetResponse = new AdGetResponse(advertisement.get());

        return adGetResponse;
    }

    @Transactional
    public List<AdGetAllResponse> getAllAdvertisement(){
        List<AdGetAllResponse> result = new ArrayList<>();
        for (Advertisement advertisement : advertisementRepository.findAll()) {
            if (advertisement != null) {
                result.add(0, new AdGetAllResponse(advertisement));
            }else{
                throw new ImageNotFoundException();
            }
        }

        return result;
    }

    @Transactional
    public String deleteAdvertisement(Long imgId){
        Optional<Advertisement> advertisement = advertisementRepository.findById(imgId);
        if(!advertisement.isPresent()) throw new ImageNotFoundException();
        advertisement.get().setDeleted(true);
        advertisementRepository.save(advertisement.get());
        return "Success";
    }

}
