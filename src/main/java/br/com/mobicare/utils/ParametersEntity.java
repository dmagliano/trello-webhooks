package br.com.mobicare.utils;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "parameter")
public class ParametersEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "`key`")
    public String key;

    @Column(name = "value")
    public String value;

    @Column(name = "description")
    public String description;

    public static Optional<ParametersEntity> findByKey(String key) {
        return find("key", key).firstResultOptional();
        //return find("key = :key", Parameters.with("key", key)).firstResultOptional();
    }
}
