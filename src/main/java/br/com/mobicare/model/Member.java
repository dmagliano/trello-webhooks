package br.com.mobicare.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "username")
    private String username;

}
