package com.code9.tennisplayer;

import com.code9.tennisplayer.exception.AlreadyExistsException;
import com.code9.tennisplayer.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void create(Player player) {
        findOneByEmail(player.getEmail());
        playerRepository.save(player);
    }

    public void update(Player player) {
        findOneById(player.getId());
        playerRepository.save(player);
    }

    public void delete(Long id) {
        findOneById(id);
        playerRepository.deleteById(id);
    }

    public Player findOneById(Long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new NotFoundException(String.format("Player with id '%d' does not exist", id));
        }
        return playerOptional.get();
    }

    public void findOneByEmail(String email) {
        Optional<Player> playerOptional =  playerRepository.findByEmail(email);
        if (playerOptional.isPresent()) {
            throw new AlreadyExistsException(String.format("Player with email '%s' already exists", email));
        }
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

}
