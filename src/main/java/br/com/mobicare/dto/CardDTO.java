package br.com.mobicare.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class CardDTO {

    private String id;

    private String idList;

    private String name;

    private String shortLink;

    public String getCardCreationDate(String cardId){
        String hexMilis = cardId.substring(0,8);
        Long milis = (Long.parseLong(hexMilis, 16)/1000);
        LocalDate date = Instant.ofEpochMilli(milis).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);
        return date.toString();
    }

}