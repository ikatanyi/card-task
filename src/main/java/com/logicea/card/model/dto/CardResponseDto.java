package com.logicea.card.model.dto;


import com.logicea.card.Enums.CardStatusEnum;
import com.logicea.card.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Kennedy ikatanyi
 */

@Data
public class CardResponseDto {

    private Long id;
    private String name;
    private String description;
    private String color;
    private CardStatusEnum status;

}
