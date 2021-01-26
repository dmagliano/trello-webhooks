package br.com.mobicare.resource;

import br.com.mobicare.model.Model;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/trello")
public class WebhookResource {

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void webhook(Model model) {
        Model.adicionar(model);
    }


}
