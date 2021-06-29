package br.com.mobicare.model;

import br.com.mobicare.dto.WebhookDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "webhook")
public class Webhook extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "webhook_id")
    private String webhookId;
    @Column(name = "board_name")
    private String boardName;
    private String description;
    @Column(name = "id_model")
    private String idModel;
    private Boolean active;

    public static Optional<Webhook> findByIdModel(String idModel) {
        return find("idModel", idModel).firstResultOptional();
    }

    public Webhook (WebhookDTO dto, String boardName) {
        // = new Webhook();
        this.webhookId = dto.getId();
        this.boardName = boardName;
        this.description = dto.getDescription();
        this.idModel = dto.getIdModel();
        this.active = dto.getActive();
    }

    public Webhook() {
    }

    public Webhook(String webhookId, String boardName, String description, String idModel, Boolean active) {
        this.webhookId = webhookId;
        this.boardName = boardName;
        this.description = description;
        this.idModel = idModel;
        this.active = active;
    }
}
