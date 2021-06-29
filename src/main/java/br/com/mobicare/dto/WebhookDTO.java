package br.com.mobicare.dto;

import br.com.mobicare.model.Webhook;
import lombok.Data;

@Data
public class WebhookDTO {

    private String id;
    private String description;
    private String idModel;
    private String callbackURL;
    private Boolean active;
}
