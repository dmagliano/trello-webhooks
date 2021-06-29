package br.com.mobicare.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "label")
public class Label extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label_id")
    private String labelId;

    @Column(name = "id_board")
    private String idBoard;

    @Column(name = "label_name")
    private String name;

    private String color;

    public static Optional<Label> findByLabelId(String labelId) {
        return find("label_id", labelId).firstResultOptional();
    }

}

