package com.itaxi.server.advertisement.presentation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdGetImageResponse {
    private byte[] Image;
    private String url;

    public AdGetImageResponse(byte[] Image,String url){
        this.Image = Image;
        this.url = url;
    }
}
