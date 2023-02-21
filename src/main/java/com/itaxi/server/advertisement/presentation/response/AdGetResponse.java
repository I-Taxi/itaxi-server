package com.itaxi.server.advertisement.presentation.response;

import com.itaxi.server.advertisement.domain.Advertisement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdGetResponse {
    private Long id;
    private String path;
    private String url;
    private String imgName;
    private String imgType;
    private String name;

    public AdGetResponse(Advertisement advertisement){
        this.id = advertisement.getId();
        this.path = advertisement.getPath();
        this.url = advertisement.getUrl();
        this.imgName = advertisement.getImgName();
        this.imgType = advertisement.getImgType();
        this.name = advertisement.getName();
    }
}
