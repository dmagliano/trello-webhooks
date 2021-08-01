package br.com.mobicare.service;

import br.com.mobicare.dto.*;
import br.com.mobicare.model.Label;
import br.com.mobicare.model.Score;
import br.com.mobicare.model.Webhook;
import br.com.mobicare.rest.TrelloService;
import br.com.mobicare.utils.ParametersService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class WebhookService {

    @Inject
    @RestClient
    TrelloService trelloService;
    @Inject
    MemberService memberService;
    @Inject
    ParametersService parametersService;

//    variaveis já estão em banco na parametersService
//    private final static String apiKey = "ab1fe54c1b3f356afd159adb19e08ab3"; //DIOGO
//    private final static String apiToken = "5b96c93354bd91fe0f10f4eee69e1f94b688017927d1ed2c21fd4ef3e7553b67"; // DIOGO
//    private static final String APIKEY = "11127c91d51bbfea99ef32954f96ce6f"; // MOBICARE
//    private static final String APITOKEN = "3c0afc3db2f870b04cd470dceeb33b797136c4c481fe1a81dd9330a13f5fcf5a"; // MOBICARE
//    private static final String CALLBACKURL = "http://359387e8c4d3.ngrok.io/trello";

    public void receive(TrelloDTO trelloDTO) {

        if (trelloDTO.getAction().getDisplay().getTranslationKey().equalsIgnoreCase("action_move_card_from_list_to_list")) {
            Score score = new Score(trelloDTO);
            score.persistAndFlush();
        }
    }

    public Response create(WebhookCreateForm webHookCreateForm) throws MalformedURLException {
        String shortURL = getShortUrl(webHookCreateForm.getBoardUrl());

        Optional<BoardDTO> board = trelloService.getBoardByShortURL(shortURL,
                parametersService.getParameterAsString("apikey"),
                parametersService.getParameterAsString("apitoken"));

        if (board.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Webhook> existingWebhook = Webhook.findByIdModel(board.get().getId());

        if (existingWebhook.isEmpty()) {
            Response newWebhook = trelloService.createWebhook(
                    parametersService.getParameterAsString("apikey"),
                    parametersService.getParameterAsString("apitoken"),
                    parametersService.getParameterAsString("callbackurl"),
                    board.get().getId(),
                    webHookCreateForm.getDescription());

            if (newWebhook.getStatus() == Response.Status.OK.getStatusCode()) {
                WebhookDTO createdWebhook = newWebhook.readEntity(WebhookDTO.class);
                Webhook webhook = new Webhook(createdWebhook, board.get().getName());
                webhook.persist();

                memberService.createMemberFromBoard(board.get().getId());
                createBoardLabels(board.get().getId());


                return Response.status(Response.Status.CREATED).entity(createdWebhook).build();
            } else {
                return newWebhook;
            }
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(existingWebhook).build();
        }
    }

    public Response removeWebhook(Long id) {
        Optional<Webhook> webhook = Webhook.findByIdOptional(id);
        if (webhook.isPresent()) {
            String webhookId = webhook.get().getWebhookId();
            trelloService.removeWebhook(webhookId,
                    parametersService.getParameterAsString("apikey"),
                    parametersService.getParameterAsString("apitoken"));

            webhook.get().delete();

            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Transactional
    public void createBoardLabels(String boardId) {
        List<LabelDTO> labels = trelloService.getBoardLabels(boardId,
                parametersService.getParameterAsString("apikey"),
                parametersService.getParameterAsString("apitoken"));

        if (labels.size() > 0) {
            for (LabelDTO label : labels) {
                Optional<Label> optional = Label.findByLabelId(label.getId());
                if (optional.isEmpty()) {
                    Label l = label.toEntity();
                    l.persistAndFlush();
                }
            }
        }

    }

    public Response getCard(String cardId) {
        Optional<CardDTO> card = trelloService.getCardById(cardId,
                parametersService.getParameterAsString("apikey"),
                parametersService.getParameterAsString("apitoken"));
        if (card.isPresent()) {
            return Response.status(Response.Status.OK).entity(card.get()).build();
        } else return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getCardCreateDate(String cardId) {
        CardDTO card = new CardDTO();
        String date = card.getCardCreationDate(cardId);
        return Response.status(Response.Status.OK).entity(date).build();
    }

    //endpoint somente para testes e alteração do callbackurl do pipedream/ngrok, só para testes.
    public Response updateWebhook(Long id, WebhookCreateForm dto) {
        Optional<Webhook> webhook = Webhook.findByIdOptional(id);
        if (webhook.isPresent()) {
            Response update = trelloService.updateWebhook(
                    webhook.get().getWebhookId(),
                    parametersService.getParameterAsString("apikey"),
                    parametersService.getParameterAsString("apitoken"),
                    dto.getBoardUrl(),
                    dto.getDescription());
            return update;

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public static Response listWebhooks() {
        List<Webhook> webhookList = Webhook.listAll();
        return Response.status(Response.Status.OK).entity(webhookList).build();
    }

    public static String getShortUrl(String boardUrl) throws MalformedURLException {
        URL url = new URL(boardUrl);
        String[] parts = url.getPath().split("/");
        return parts[2];
    }

}


