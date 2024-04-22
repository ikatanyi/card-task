package com.logicea.card.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.HashSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * User's Role
 *
 * @author Kennedy ikatanyi
 */
@Entity
@Data
@Table(name = "auth_role")
public class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_disabled", columnDefinition = "boolean default false", nullable = false)
    private Boolean disabled;

    protected RoleEntity() {
    }

    public RoleEntity(final String name, final String description) {
        this.name = name.trim();
        this.description = description.trim();
        this.disabled = false;
    }

}
