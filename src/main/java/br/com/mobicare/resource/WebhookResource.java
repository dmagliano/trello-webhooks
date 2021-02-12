package br.com.mobicare.resource;


import br.com.mobicare.dto.TrelloDTO;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/trello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebhookResource {

    @POST
    @Transactional
    public void receiveWebhook(TrelloDTO trelloDTO) {

        System.out.println(trelloDTO);

    }


}
