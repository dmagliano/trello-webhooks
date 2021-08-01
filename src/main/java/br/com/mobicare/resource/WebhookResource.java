package br.com.mobicare.resource;

import br.com.mobicare.dto.*;
import br.com.mobicare.service.MemberService;
import br.com.mobicare.service.WebhookService;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;

@Path("/trello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebhookResource {

    private final WebhookService webhookService;
    private final MemberService memberService;

    public WebhookResource(WebhookService webhookService, MemberService memberService) {
        this.webhookService = webhookService;
        this.memberService = memberService;
    }

    //TODO ocultar do swagger
    @HEAD
    public Response callbacktest() {
        return Response.status(Response.Status.OK).build();
    }

    //TODO ocultar do swagger
    @POST
    @Transactional
    public void receiveWebhook(TrelloDTO trelloDTO) {
        webhookService.receive(trelloDTO);
    }

    @GET
    @Path("/webhooks")
    public Response listWebhooks() {
        return webhookService.listWebhooks();
    }

    //TODO ajustar o persist para facilitar o controle de webhooks existentes
    @Path("/webhooks")
    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Webhook criado com sucesso")
    @APIResponse(responseCode = "401", description = "Você não tem permissão para acessar este board")
    @APIResponse(responseCode = "404", description = "URL incorreta ou board não encontrado")
    @APIResponse(responseCode = "406", description = "Já existe um webhook para este board")
    public Response createWebhook(WebhookCreateForm webHookCreateForm) throws MalformedURLException {
        return webhookService.create(webHookCreateForm);
    }

    //endpoint somente para testes e alteração do callbackurl do pipedream/ngrok, só para testes.
    @PUT
    @Path("/webhooks/{id}")
    @Transactional
    public Response updateWebhook(@PathParam("id") Long id, WebhookCreateForm dto){
        return webhookService.updateWebhook(id, dto);
    }

    @DELETE
    @Path("/webhooks/{id}")
    @Transactional
    @APIResponse(responseCode = "204", description = "Webhook deletado com suceso")
    public Response removeWebhook(@PathParam("id") Long id) {
        return webhookService.removeWebhook(id);
    }

    @GET
    @Path("/webhooks/card/{id}")
    public Response getCard(@PathParam("id") String id)  {
        return webhookService.getCard(id);
    }

    @GET
    @Path("/webhooks/card/date/{id}")
    public Response getCardDate(@PathParam("id") String id)  {
        return webhookService.getCardCreateDate(id);
    }
}
