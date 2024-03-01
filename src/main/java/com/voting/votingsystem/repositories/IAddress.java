package com.voting.votingsystem.repositories;

import com.voting.votingsystem.Entities.EAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddress extends JpaRepository<EAddress, Long> {
    EAddress findById(long id);

}

