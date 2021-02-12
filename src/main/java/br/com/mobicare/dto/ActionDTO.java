package br.com.mobicare.dto;

import br.com.mobicare.model.Action;
import lombok.Data;

@Data
public class ActionDTO {

    private String id;

    private String type;

    private String date;

    private DataDTO data;

    public Action toEntity() {
        Action action = new Action();
        action.setActionId(this.id);
        action.setType(this.type);
        action.setDate(this.date);
        return action;
    }
}
