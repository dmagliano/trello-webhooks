package br.com.mobicare.dto;

import br.com.mobicare.model.Label;
import lombok.Data;

@Data
public class LabelDTO {
    private String id;
    private String idBoard;
    private String name;
    private String color;

    public Label toEntity(){
        Label label = new Label();
        label.setLabelId(this.id);
        label.setIdBoard(this.idBoard);
        label.setName(this.name);
        label.setColor(this.color);
        return label;
    }
}
