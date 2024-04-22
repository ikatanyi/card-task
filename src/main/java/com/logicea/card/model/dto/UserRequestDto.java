package com.logicea.card.model.dto;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.logicea.card.Enums.RoleEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public record UserRequestDto(
    String name,
    String email,
    String password,
    RoleEnum roles

){

}
