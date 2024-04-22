package com.logicea.card.validator;

import static com.logicea.card.constants.Constant.CARD_NAME;
import static com.logicea.card.constants.Constant.ERROR_EMPTY_FIELD;

import com.logicea.card.exception.APIException;
import com.logicea.card.model.dto.CardRequestDto;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CardRequestValidator implements Validator {

    protected final ResourceBundleMessageSource messageSource;

    public CardRequestValidator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CardRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CardRequestDto cardRequestDto = (CardRequestDto) target;
        validateRequiredFields(cardRequestDto, errors);

        if (!errors.hasErrors()) {
            validateCardRequestDto(cardRequestDto, errors);
        }
    }

    private void validateRequiredFields(CardRequestDto cardRequestDto, Errors errors) {

        if (cardRequestDto.getName() == null || cardRequestDto.getName().isBlank()) {
            throw APIException.badRequest("Invalid blank required field: name");
        }
    }

    private void validateCardRequestDto(CardRequestDto request, Errors errors) {

        if (!isValidColor(request.getColor())) {
            throw APIException.badRequest("Invalid Color code:"+request.getColor());
        }

    }

    public static Boolean isValidColor(String color) {
        if (!color.isEmpty() && !color.isBlank()) {
            if (color.length()-1 != 6) {
                return false;
            } else if (!isValidColorPattern(color)) {
                return false;
            } else  {
                return true;
            }

        } else {
            return true;
        }
    }

    private static boolean isValidColorPattern(String color) {
        Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
        Matcher m = colorPattern.matcher(color);
        return m.matches();
    }
}
