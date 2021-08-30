package com.code9.tenniscourt.dto;

import com.code9.tenniscourt.model.TennisCourt;
import org.springframework.stereotype.Component;

@Component
public class TennisCourtMapper {

    public TennisCourt dtoToEntity(TennisCourtDto dto) {
        return  TennisCourt.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public TennisCourtDto entityToDto(TennisCourt court) {
        return TennisCourtDto.builder()
                .id(court.getId())
                .name(court.getName())
                .build();
    }

    public TennisCourt dtoToNewEntity(TennisCourtDto dto) {
        return  TennisCourt.builder()
                .name(dto.getName())
                .build();
    }

}
