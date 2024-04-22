package com.logicea.card.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CardStatusEnum {
  @JsonProperty("To Do")
  ToDo("To Do"),
  @JsonProperty("In Progress")
  InProgress("In Progress"),
  @JsonProperty("Done")
  Done("Done");

  CardStatusEnum(String status){
  }
}
