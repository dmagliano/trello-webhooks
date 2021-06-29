package br.com.mobicare.dto;

import lombok.Data;

@Data
public class WebhookCreateForm {

    private String boardUrl;
    private String description;

}
