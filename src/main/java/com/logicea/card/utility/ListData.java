package com.logicea.card.utility;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Kennedy ikatanyi
 */
@Data
@RequiredArgsConstructor
public class ListData<T> {

    private String code;
    private String message;
    private List<T> content;


}
