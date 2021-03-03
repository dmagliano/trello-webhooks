package br.com.mobicare.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Board {

    @Id
    private Long id;

    private String boardId;

    private String name;

    private String shortLink;


}
