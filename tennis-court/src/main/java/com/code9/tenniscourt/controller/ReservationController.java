package com.code9.tenniscourt.controller;

import com.code9.tenniscourt.dto.ReservationDto;
import com.code9.tenniscourt.dto.ReservationMapper;
import com.code9.tenniscourt.dto.TennisCourtDto;
import com.code9.tenniscourt.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean createReservation(@Valid @RequestBody final ReservationDto dto) {
        return reservationService.create(reservationMapper.dtoToNewEntity(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") final Long id) {
        reservationService.delete(id);
    }

    @GetMapping("/{id}")
    public ReservationDto getOne(@PathVariable("id") Long id) {
        return reservationMapper.entityToDto(reservationService.findOneById(id));
    }

    @GetMapping
    public List<ReservationDto> getAll() {
        return reservationService.findAll()
                .stream()
                .map(reservationMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-page")
    public Page<ReservationDto> getAll(Pageable pageable) {
        return reservationService.findAll(pageable)
                .map(reservationMapper::entityToDto);
    }

    @PutMapping("payment/{id}")
    public void processPayment(@PathVariable("id") Long id) {
        reservationService.processPayment(id);
    }

}
