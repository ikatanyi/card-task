package com.logicea.card.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SortEnum {
  NAME ("name"),
  COLOR("color"),
  STATUS("status"),
  CREATIONDATE("createdOn");

  public final String value;
  SortEnum(String value) {
    this.value = value;
  }
}
