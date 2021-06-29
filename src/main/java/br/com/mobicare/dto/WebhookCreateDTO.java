package br.com.mobicare.dto;

import lombok.Data;

@Data
public class WebhookCreateDTO {

    private String key;
    private String token;
    private String idModel;
    private String description;
    private String callbackUrl;

    public WebhookCreateDTO(WebhookCreateForm webHookCreateForm, String callbackUrl, String idModel, String key, String token) {
        this.key = key;
        this.token = token;
        this.idModel = idModel;
        this.description = webHookCreateForm.getDescription();
        this.callbackUrl = callbackUrl;
    }
}
