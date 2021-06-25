package com.code9.tenniscourt.service;

import com.code9.tenniscourt.exception.AlreadyExistsException;
import com.code9.tenniscourt.exception.NotFoundException;
import com.code9.tenniscourt.model.TennisCourt;
import com.code9.tenniscourt.repository.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TennisCourtService {

    private final TennisCourtRepository tennisCourtRepository;

    public void create(TennisCourt court) {
        findOneByName(court.getName());
        tennisCourtRepository.save(court);
    }

    public void update(TennisCourt updatedCourt) {
        TennisCourt oldCourt = findOneById(updatedCourt.getId());
        checkNameIsUnique(oldCourt.getName(), updatedCourt.getName());
        tennisCourtRepository.save(updatedCourt);
    }

    public void delete(Long id) {
        findOneById(id);
        tennisCourtRepository.deleteById(id);
    }

    public TennisCourt findOneById(Long id) {
        Optional<TennisCourt> courtOptional = tennisCourtRepository.findById(id);
        if (courtOptional.isEmpty()) {
            throw new NotFoundException(String.format("Tennis court with id '%d' does not exist", id));
        }
        return courtOptional.get();
    }

    public void findOneByName(String name) {
        Optional<TennisCourt> courtOptional =  tennisCourtRepository.findByName(name);
        if (courtOptional.isPresent()) {
            throw new AlreadyExistsException(String.format("Tennis court with name '%s' already exists", name));
        }
    }

    public List<TennisCourt> findAll() {
        return tennisCourtRepository.findAll();
    }

    public Page<TennisCourt> findAll(Pageable pageable) {
        return tennisCourtRepository.findAll(pageable);
    }

    private void checkNameIsUnique(String oldName, String newName) {
        if(!oldName.equals(newName)) {
            findOneByName(newName);
        }
    }

}
