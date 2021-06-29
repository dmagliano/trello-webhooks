package br.com.mobicare.service;

import br.com.mobicare.dto.MemberDTO;
import br.com.mobicare.model.Member;
import br.com.mobicare.rest.TrelloService;
import br.com.mobicare.utils.ParametersService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MemberService {

    @Inject
    @RestClient
    TrelloService trelloService;
    @Inject
    ParametersService parametersService;

    public void createMember(MemberDTO member) {
        Optional<Member> optional = Member.findByMemberId(member.getId());
        if (optional.isEmpty()) {
            Member m = member.toEntity();
            m.persistAndFlush();
        }
    }

    public void createMemberFromBoard(String boardId) {
        List<MemberDTO> boardMembers = trelloService.getBoardMembers(boardId,
                parametersService.getParameterAsString("apikey"),
                parametersService.getParameterAsString("apitoken"));
        for (MemberDTO member : boardMembers) {
            createMember(member);
        }
    }
}
