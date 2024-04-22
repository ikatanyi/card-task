package com.logicea.card.service;

import com.logicea.card.Enums.CardStatusEnum;
import com.logicea.card.Enums.SortEnum;
import com.logicea.card.model.dto.CardRequestDto;
import com.logicea.card.model.dto.CardResponseDto;
import com.logicea.card.model.dto.RoleDto;
import com.logicea.card.model.entity.CardEntity;
import com.logicea.card.model.entity.RoleEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

  CardResponseDto createCard(CardRequestDto data);

  CardEntity getCard(Long id);

  Page<CardResponseDto> retrieveAllCards(String name, String color, CardStatusEnum status, LocalDate createdOn, SortEnum sortBy, Pageable page);

  CardResponseDto updateCardStatus(Long id, CardStatusEnum status);

  CardResponseDto updateCard(Long id, CardRequestDto data);

  void deleteCard(Long cardId);

}
