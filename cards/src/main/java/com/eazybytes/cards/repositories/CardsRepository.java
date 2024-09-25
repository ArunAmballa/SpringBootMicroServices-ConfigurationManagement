package com.eazybytes.cards.repositories;

import com.eazybytes.cards.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
