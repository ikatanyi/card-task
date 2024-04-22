package com.logicea.card.model.dto;


import com.logicea.card.Enums.CardStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Kennedy ikatanyi
 */

@Data
public class CardRequestDto {

    @Schema(example = "card-name")
    private String name;
    @Schema(example = "description")
    private String description;
    @Schema(example = "#678542")
    private String color;
    @Schema(example = "To Do")
    private CardStatusEnum status;

}
