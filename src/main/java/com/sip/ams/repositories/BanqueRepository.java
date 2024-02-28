package com.sip.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Banque;

@Repository
public interface BanqueRepository  extends CrudRepository<Banque, Long> {

}
