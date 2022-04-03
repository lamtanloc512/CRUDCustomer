package com.fsoft.app.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    public Role() {
        super();
    }

    /**
     * @param role
     */
    public Role(String role) {
        super();
        this.role = role;
    }



}
