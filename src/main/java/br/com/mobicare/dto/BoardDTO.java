package br.com.mobicare.dto;

import br.com.mobicare.model.Board;
import lombok.Data;

@Data
public class BoardDTO {

    private String id;

    private String name;

    private String shortLink;

    public Board toEntity() {
        Board board = new Board();
        board.setBoardId(this.id);
        board.setName(this.name);
        board.setShortLink(this.shortLink);
        return board;
    }

}
