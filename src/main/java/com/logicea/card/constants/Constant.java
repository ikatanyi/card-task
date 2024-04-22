package com.logicea.card.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

  public static final String CARD_NAME = "Card name";
  public static final int COLOR_DIGITS = 6;
  public static final String COLOR_PREFIX = "#";

  public static final String ERROR_EMPTY_FIELD = "Required field blank error";

}