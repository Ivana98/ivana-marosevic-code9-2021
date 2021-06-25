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

    public void update(Player updatedPlayer) {
        Player oldPlayer = findOneById(updatedPlayer.getId());
        checkEmailIsUnique(oldPlayer, updatedPlayer.getEmail());
        playerRepository.save(updatedPlayer);
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
        /*
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedPlayersFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Player> players =  playerRepository.findAll();
        session.disableFilter("deletedPlayersFilter");
        return players;
        */

        return playerRepository.findAll();
    }

    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    private void checkEmailIsUnique(Player oldPlayer, String newEmail) {
        // if email is not changed, it remains unique
        if(!oldPlayer.getEmail().equals(newEmail)) {
            findOneByEmail(newEmail);
        }
    }

}
