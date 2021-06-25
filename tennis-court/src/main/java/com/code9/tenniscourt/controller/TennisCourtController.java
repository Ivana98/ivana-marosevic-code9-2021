package com.code9.tenniscourt.controller;

import com.code9.tenniscourt.dto.TennisCourtDto;
import com.code9.tenniscourt.dto.TennisCourtMapper;
import com.code9.tenniscourt.service.TennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/tennis-courts")
@AllArgsConstructor
public class TennisCourtController {

    private final TennisCourtService tennisCourtService;
    private final TennisCourtMapper tennisCourtMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTennisCourt(@Valid @RequestBody final TennisCourtDto dto) {
        tennisCourtService.create(tennisCourtMapper.dtoToNewEntity(dto));
    }

    @PutMapping
    public void updateTennisCourt(@Valid @RequestBody final TennisCourtDto dto) {
        tennisCourtService.update(tennisCourtMapper.dtoToEntity(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteTennisCourt(@PathVariable("id") final Long id) {
        tennisCourtService.delete(id);
    }

    @GetMapping("/{id}")
    public TennisCourtDto getOne(@PathVariable("id") Long id) {
        return tennisCourtMapper.entityToDto(tennisCourtService.findOneById(id));
    }

    @GetMapping
    public List<TennisCourtDto> getAll() {
        return tennisCourtService.findAll()
                .stream()
                .map(tennisCourtMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-page")
    public Page<TennisCourtDto> getAll(Pageable pageable) {
        return tennisCourtService.findAll(pageable)
                .map(tennisCourtMapper::entityToDto);
    }

}
