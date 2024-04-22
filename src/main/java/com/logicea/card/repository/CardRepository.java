package com.logicea.card.repository;

import com.logicea.card.model.entity.CardEntity;
import com.logicea.card.model.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kennedy ikatanyi
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long>,
    JpaSpecificationExecutor<CardEntity> {

    Optional<CardEntity> findByIdAndUser(final Long id, UserEntity user);

    Optional<CardEntity> findByName(final String name);

}
