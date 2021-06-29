package br.com.mobicare.model;

import br.com.mobicare.utils.ParametersEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "member")
public class Member extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    public static Optional<Member> findByMemberId(String memberId) {
        return find("member_id", memberId).firstResultOptional();
        //return find("key = :key", Parameters.with("key", key)).firstResultOptional();
    }

}
