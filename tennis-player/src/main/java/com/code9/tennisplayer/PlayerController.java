package com.code9.tennisplayer;

import com.code9.tennisplayer.dto.PlayerDto;
import com.code9.tennisplayer.dto.PlayerMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody final PlayerDto dto) {
        playerService.create(playerMapper.dtoToNewEntity(dto));
    }

    @PutMapping
    public void updateProduct(@Valid @RequestBody final PlayerDto dto) {
        playerService.update(playerMapper.dtoToEntity(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final Long id) {
        playerService.delete(id);
    }

    @GetMapping("/{id}")
    public PlayerDto getOne(@PathVariable("id") Long id) {
        return playerMapper.entityToDto(playerService.findOneById(id));
    }

    @GetMapping
    public List<PlayerDto> getAll() {
        return playerService.findAll()
                .stream()
                .map(playerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-page")
    public Page<PlayerDto> getAll(Pageable pageable) {
        return playerService.findAll(pageable)
                .map(playerMapper::entityToDto);
    }

}
