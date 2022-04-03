package com.fsoft.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String createdAt;
    private String updatedAt;

    @OneToMany(mappedBy = "order")
    private Set<Pizza> listPizza = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_uuid", nullable = false, referencedColumnName = "uuid")
    private Customer customer;

}
