package com.logicea.card.controller;

import static com.logicea.card.utility.common.PaginationUtil.toPager;

import com.logicea.card.Enums.CardStatusEnum;
import com.logicea.card.Enums.SortEnum;
import com.logicea.card.model.dto.CardRequestDto;
import com.logicea.card.model.dto.CardResponseDto;
import com.logicea.card.service.CardService;
import com.logicea.card.utility.Pager;
import com.logicea.card.utility.common.PaginationUtil;
import com.logicea.card.validator.CardRequestValidator;
import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kennedy
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    private final CardRequestValidator cardRequestValidator;

    @InitBinder("cardRequestDto")
    public void initSetCardRequestRequestDTOValidator(WebDataBinder binder) {
        binder.addValidators(cardRequestValidator);
    }


    @PostMapping("/card")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<?> createCard(@Valid @RequestBody CardRequestDto cardRequestDto) {

        var card = cardService.createCard(cardRequestDto);

        Pager<CardResponseDto> pagers = new Pager();
        pagers.setMessage("Card created successfull");
        pagers.setContent(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagers);

    }

    @GetMapping("/card/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<?> getCard(@PathVariable(value = "id") Long id) {
        var item = cardService.getCard(id);

        return ResponseEntity.ok(item);
    }

    @PutMapping("/card/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<?> updateCard(@PathVariable(value = "id") Long id, @Valid @RequestBody CardRequestDto cardRequestDto) {

        var result = cardService.updateCard(id, cardRequestDto);

        Pager<CardResponseDto> pagers = new Pager();
        pagers.setMessage("Item update successful");
        pagers.setContent(result);
        return ResponseEntity.ok(pagers);

    }

    @GetMapping("/card")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
    public ResponseEntity<?> getAllCards(
            @RequestParam(value = "color", required = false) final String color,
            @RequestParam(value = "creationOnDate", required = false) final LocalDate creationOnDate,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "status", required = false) final CardStatusEnum status,
            @RequestParam(value = "sortBy", required = false) final SortEnum sortBy,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer size) {

        Pageable pageable = PaginationUtil.createPage(page, size);

        Page<CardResponseDto> list = cardService.retrieveAllCards(name, color, status, creationOnDate, sortBy, pageable);

        var usercards = toPager(list, "User Cards Retrived successfully");
        return ResponseEntity.ok(usercards);
    }

    @DeleteMapping("/card/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<?> deleteCard(
        @RequestParam(value = "id", required = false) final Long id
    ) {
        cardService.deleteCard(id);
        return ResponseEntity.ok("Card deleted successfully");
    }
}
