package com.restapi.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ing_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingId;

    @Column(name = "ing_name", nullable = false)
    private String ingName;

    @Column(name = "ing_image", nullable = false)
    private String ingImage;

    @Column(name = "ing_state", nullable = false)
    private Boolean ingState;

}
