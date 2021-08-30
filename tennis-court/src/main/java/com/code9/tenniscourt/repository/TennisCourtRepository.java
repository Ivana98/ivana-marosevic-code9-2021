package com.code9.tenniscourt.repository;

import com.code9.tenniscourt.model.TennisCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TennisCourtRepository extends JpaRepository<TennisCourt, Long>  {

    Optional<TennisCourt> findByName(String name);

}
