package com.itaxi.server.advertisement.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdCreateRequest {
    private String path;
    private String url;
    private String imgName;
    private String imgType;
    private String name;
}
