package com.logicea.card.utility;
  
import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 *
 * @author Kennedy
 */

@Data
@RequiredArgsConstructor
public class PageDetails {

    private Integer page;
    private Integer perPage;
    private Integer totalPage;
    private Long totalElements;
    private String name;


}
