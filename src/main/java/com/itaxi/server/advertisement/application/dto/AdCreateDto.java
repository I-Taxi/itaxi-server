package com.itaxi.server.advertisement.application.dto;

import com.itaxi.server.advertisement.presentation.request.AdCreateRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdCreateDto {
    private String path;
    private String url;
    private String imgName;
    private String imgType;
    private String name;
    public AdCreateDto(AdCreateRequest request){
        this.path = request.getPath();
        this.url = request.getUrl();
        this.imgName = request.getImgName();
        this.imgType = request.getImgType();
        this.name = request.getName();
    }
}
