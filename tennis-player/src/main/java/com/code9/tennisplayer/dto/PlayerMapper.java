package com.code9.tennisplayer.dto;

import com.code9.tennisplayer.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player dtoToEntity(PlayerDto dto) {
        return  Player.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .birthdate(dto.getBirthdate())
                .build();
    }

    public PlayerDto entityToDto(Player player) {
        return PlayerDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .email(player.getEmail())
                .birthdate(player.getBirthdate())
                .build();
    }

    public Player dtoToNewEntity(PlayerDto dto) {
        return  Player.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .birthdate(dto.getBirthdate())
                .build();
    }

}
