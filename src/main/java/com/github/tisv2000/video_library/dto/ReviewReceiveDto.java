package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewReceiveDto {
    Integer id;
    UserReceiveDto user;
    MovieReceiveDto movie;
    String text;
    Integer rate;
}
