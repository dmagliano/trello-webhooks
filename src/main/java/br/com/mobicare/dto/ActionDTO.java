package br.com.mobicare.dto;

import lombok.Data;

@Data
public class ActionDTO {

    private String id;

    private String type;

    private String date;

    private DataDTO data;

    private MemberDTO memberCreator;

    private DisplayDTO display;
}
