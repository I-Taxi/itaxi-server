package com.itaxi.server.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.member.MemberEmailNullException;
import com.itaxi.server.exception.member.MemberNameNullException;
import com.itaxi.server.exception.member.MemberPhoneNullException;
import com.itaxi.server.exception.member.MemberUidNullException;
import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.member.application.dto.MemberCreateRequestDTO;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.report.domain.Report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.http.HttpStatus;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uid;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String name;

    private String bank;

    private int penalty;

    private String bankAddress;

    @OneToMany(mappedBy = "member")
    private List<Joiner> joiners = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<KTXJoiner> ktxJoiners = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<FavorJoiner> favorJoiners = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Report> reports = new ArrayList<>();



    public Member(MemberCreateRequestDTO memberCreateRequestDTO) {
        this.uid = memberCreateRequestDTO.getUid();
        this.email = memberCreateRequestDTO.getEmail();
        this.phone = memberCreateRequestDTO.getPhone();
        this.name = memberCreateRequestDTO.getName();
        this.bank = memberCreateRequestDTO.getBank();
        this.bankAddress = memberCreateRequestDTO.getBankAddress();

        /* data validation - uid */
        if(this.uid == null || this.uid.trim().isEmpty())
            throw new MemberUidNullException(HttpStatus.INTERNAL_SERVER_ERROR);

        /* data validation - email */
        if(this.email == null || this.email.trim().isEmpty())
            throw new MemberEmailNullException(HttpStatus.INTERNAL_SERVER_ERROR);

        /* data validation - phone */
        if(this.phone == null || this.phone.trim().isEmpty())
            throw new MemberPhoneNullException(HttpStatus.INTERNAL_SERVER_ERROR);

        /* data validation - name */
        if(this.name == null || this.name.trim().isEmpty())
            throw new MemberNameNullException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
