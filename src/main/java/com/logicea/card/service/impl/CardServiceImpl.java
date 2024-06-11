package com.logicea.card.service.impl;

import com.logicea.card.Enums.CardStatusEnum;
import com.logicea.card.Enums.SortEnum;
import com.logicea.card.exception.APIException;
import com.logicea.card.mapper.CardMapper;
import com.logicea.card.model.dto.CardRequestDto;
import com.logicea.card.model.dto.CardResponseDto;
import com.logicea.card.model.entity.CardEntity;
import com.logicea.card.repository.CardRepository;
import com.logicea.card.service.CardService;
import com.logicea.card.specification.CardSpecification;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kennedy Ikatanyi
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserServiceImpl userService;

    @Override
    public CardResponseDto createCard(CardRequestDto cardRequestDto) {
        var user = userService.getAuthenticatedUser();

       findByName(cardRequestDto.getName()).ifPresent(card ->{
           throw APIException.conflict("Card with name {0} Already Exists",card.getName());}
       );

        var cardEntity = cardMapper.mapCardRequestDtoToCardEntity(cardRequestDto);
        cardEntity.setUser(user);

        return cardMapper.mapCardEntityToCardResponseDto(
            cardRepository.save(cardEntity));
    }

    public CardEntity getCard(Long id) {
        var user = userService.getAuthenticatedUser();
        return cardRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> APIException.notFound("Card with id {0} does not exists.", id));
    }

    public Optional<CardEntity> findByName(String name) {
        return cardRepository.findByName(name);
    }

    public Page<CardResponseDto> retrieveAllCards(String name, String color, CardStatusEnum status, LocalDate createdOn, SortEnum sortBy, Pageable page) {
        var user = userService.getAuthenticatedUser();

        if(!user.getRoles().contains("ADMIN"))
             user = null;

        Specification<CardEntity> spec = CardSpecification.createSpecification(name, color, status, createdOn, sortBy, user);

        return cardRepository.findAll(spec, page)
            .map(cardMapper::mapCardEntityToCardResponseDto);
    }

    public CardResponseDto updateCardStatus(Long id, CardStatusEnum status) {
        CardEntity role = this.getCard(id);
        role.setStatus(status);
        return cardMapper.mapCardEntityToCardResponseDto(cardRepository.save(role));
    }

    public CardResponseDto updateCard(Long id, CardRequestDto data) {
        CardEntity card = this.getCard(id);

        var roleEntity = cardRepository.save(card);
        return cardMapper.mapCardEntityToCardResponseDto(roleEntity);
    }

    public void deleteCard(Long cardId) {
         var card = this.getCard(cardId);
        cardRepository.delete(card);
    }
}
