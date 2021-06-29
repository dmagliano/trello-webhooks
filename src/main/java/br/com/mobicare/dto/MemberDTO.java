package br.com.mobicare.dto;


import br.com.mobicare.model.Member;
import lombok.Data;


@Data
public class MemberDTO {

    private String id;
    private String username;
    private String fullName;

    public Member toEntity(){
        Member member = new Member();
        member.setMemberId(this.id);
        member.setUsername(this.username);
        member.setFullName(this.fullName);
        return member;
    }

}
