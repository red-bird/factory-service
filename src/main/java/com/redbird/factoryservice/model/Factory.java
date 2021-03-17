package com.redbird.factoryservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "factories")
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String name;
//    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    List<GoodInfo> GoodInfoList;
}
