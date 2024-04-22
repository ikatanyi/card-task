package com.logicea.card.model.entity;


import com.logicea.card.Enums.CardStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

/**
 * User's Role
 *
 * @author Kennedy ikatanyi
 */
@Entity
@Data
@Table(name = "task_card")
public class CardEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description",nullable = true)
    private String description;
    @Column(name = "color",nullable = true)
    private String color;
    @Column(name = "card_status")
    private CardStatusEnum status;

    @CreatedDate
    protected LocalDate createdOn;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "fk_card_user_id"))
    private UserEntity user;
}
