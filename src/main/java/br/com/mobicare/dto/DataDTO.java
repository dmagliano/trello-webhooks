package br.com.mobicare.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DataDTO {

    private CardDTO card;

    private BoardDTO board;

    private Map<String, Object> listBefore;

    private Map<String, Object> listAfter;


}
