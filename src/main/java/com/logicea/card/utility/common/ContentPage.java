package com.logicea.card.utility.common;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 *
 * @author kennedy ikatanyi
 */
@Data
public class ContentPage<T> {

    private Integer page;
    private Long totalResults;
    private Integer pageSize;
    private Integer totalPages;
    private List<T> results;
 
    public static ContentPage createPage(Page page) {
        final ContentPage contentPage = new ContentPage<>();
        contentPage.setTotalPages(page.getTotalPages());
        contentPage.setPage(page.getNumber()+1);
        contentPage.setTotalResults(page.getTotalElements());
        contentPage.setPageSize(page.getSize());
        if(page.getSize()>0){
            final ArrayList list=new ArrayList<>(page.getSize());
            contentPage.setResults(list);
            page.forEach(e-> list.add(e));
        }

        return contentPage;
    }
}
