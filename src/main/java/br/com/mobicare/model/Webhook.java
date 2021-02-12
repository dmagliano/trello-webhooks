package br.com.mobicare.model;

import lombok.Data;

@Data
public class Webhook {

    private String id;
    private String description;
    private String idModel;
    private String callbackUrl;
    private Boolean active;
    private int consecutiveFailures;

}
