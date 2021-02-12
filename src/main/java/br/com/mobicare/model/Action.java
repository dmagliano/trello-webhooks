package br.com.mobicare.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "action")
public class Action extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_id")
    private String actionId;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private String date;

}
