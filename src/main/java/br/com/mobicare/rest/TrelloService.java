package br.com.mobicare.rest;

import br.com.mobicare.dto.BoardDTO;
import br.com.mobicare.dto.CardDTO;
import br.com.mobicare.dto.MemberDTO;
import br.com.mobicare.dto.WebhookDTO;
import br.com.mobicare.utils.ClientObjectMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/1")
@RegisterRestClient(configKey = "trello-api")
@Produces("application/json")
@Consumes("application/json")
@RegisterProvider(ClientObjectMapper.class)
public interface TrelloService {

    @GET
    @Path("/cards/{id}")
    Optional<CardDTO> getCardById(@PathParam("id") String cardId,
                                  @QueryParam("key") String key,
                                  @QueryParam("token") String token);

    @GET
    @Path("/boards/{shortURL}")
    Optional<BoardDTO> getBoardByShortURL(@PathParam("shortURL") String shortURL,
                                          @QueryParam("key") String key,
                                          @QueryParam("token") String token);

    @GET
    @Path("/boards/{boardId}/members")
    List<MemberDTO> getBoardMembers(@PathParam("boardId") String boardId,
                                    @QueryParam("key") String key,
                                    @QueryParam("token") String token);

    @POST
    @Path("/webhooks/")
    Response createWebhook(@QueryParam("key") String key,
                           @QueryParam("token") String token,
                           @QueryParam("callbackURL") String callbackURL,
                           @QueryParam("idModel") String idModel,
                           @QueryParam("description") String description);

    @DELETE
    @Path("/webhooks/{id}")
    Response removeWebhook(@PathParam("id") String webhookId,
                           @QueryParam("key") String key,
                           @QueryParam("token") String token);

    @PUT
    @Path("/webhooks/{id}")
    Response updateWebhook(@PathParam("id") String webhookId,
                           @QueryParam("key") String key,
                           @QueryParam("token") String token,
                           @QueryParam("callbackURL") String callbackURL,
                           @QueryParam("description") String description);

    @GET
    @Path("/tokens/{token}/webhooks")
    List<WebhookDTO> listWebhook(@PathParam("token") String token,
                                 @QueryParam("key") String key);


}
