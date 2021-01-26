package br.com.mobicare.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private String cardId;

    @Column(name = "name")
    private String cardName;

    @Column(name = "id_list")
    private String IdList;

}
