package br.com.mobicare.model;

import br.com.mobicare.dto.TrelloDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "score")
public class Score extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "member_id")
    private String memberId;
    @Column(name = "board_id")
    private String boardId;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "action_id")
    private String actionId;
    @Column(name = "translation_key")
    private String translationKey;
    @Column(name = "list_before")
    private String listBefore;
    @Column(name = "list_after")
    private String listAfter;
    private String date;
    private String type;

    public Score() {
    }

    public Score(TrelloDTO trelloDTO) {
        this.memberId = trelloDTO.getAction().getMemberCreator().getId();
        this.boardId = trelloDTO.getAction().getData().getBoard().getId();
        this.cardId = trelloDTO.getAction().getData().getCard().getId();
        this.actionId = trelloDTO.getAction().getId();
        this.translationKey = trelloDTO.getAction().getDisplay().getTranslationKey();
        this.listBefore = trelloDTO.getAction().getData().getListBefore().get("name");
        this.listAfter = trelloDTO.getAction().getData().getListAfter().get("name");
        this.date = trelloDTO.getAction().getDate();
        this.type = trelloDTO.getAction().getType();
    }
}
