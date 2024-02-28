package com.sip.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Actualite;

@Repository
public interface ActualiteRepository extends CrudRepository<Actualite, Long> {

}
