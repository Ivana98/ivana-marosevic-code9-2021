package com.code9.tenniscourt.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class TennisCourtDto {

    Long id;

    @NotBlank(message = "Name is mandatory")
    String name;

}
