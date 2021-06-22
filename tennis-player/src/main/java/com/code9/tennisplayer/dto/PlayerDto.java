package com.code9.tennisplayer.dto;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Value
@Builder
public class PlayerDto {

    Long id;

    @NotBlank(message = "First name is mandatory")
    String firstName;

    @NotBlank(message = "Last name is mandatory")
    String lastName;

    @Email(message = "Email format is not valid")
    @NotBlank(message = "First name is mandatory")
    String email;

    @Past(message = "Date of birth must be past date")
    LocalDate birthdate;

}
