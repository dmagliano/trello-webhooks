package br.com.mobicare.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "model")
public class Model extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private String model_id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    //private String labelNames;

}
