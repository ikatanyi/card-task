package com.logicea.card.specification;

import com.logicea.card.Enums.CardStatusEnum;
import com.logicea.card.Enums.SortEnum;
import com.logicea.card.model.entity.CardEntity;
import com.logicea.card.model.entity.UserEntity;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filters for searching the data and filtering for the user
 *
 * @author Kennedy Ikatanyi
 */
public class CardSpecification {

    public CardSpecification() {
        super();
    }

    public static Specification<CardEntity> createSpecification(String name, String color, CardStatusEnum status, LocalDate createdOn, SortEnum sortBy,UserEntity user) {
        return (root, query, cb) -> {
            final ArrayList<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(cb.equal(root.get("name"), name));
            }
            if (color != null) {
                predicates.add(cb.equal(root.get("color"), color));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (createdOn != null) {
                predicates.add(cb.equal(root.get("createdOn"), createdOn));
            }
            if (user != null) {
                predicates.add(cb.equal(root.get("user"), user));
            }

            //Sort by name,color, status
            // You can sort by one value at time, but we can change the value to take a list to sort by all the attributes
            if(sortBy!=null) {
                query.orderBy(cb.asc(root.get(sortBy.value)));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
