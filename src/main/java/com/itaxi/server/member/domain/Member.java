package com.itaxi.server.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.banner.domain.Banner;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.exception.member.MemberEmailEmptyException;
import com.itaxi.server.exception.member.MemberNameEmptyException;
import com.itaxi.server.exception.member.MemberPhoneEmptyException;
import com.itaxi.server.exception.member.MemberUidEmptyException;
import com.itaxi.server.favorite.domain.FAVORJoiner;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.member.application.dto.MemberCreateRequestDTO;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.report.domain.Report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uid;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    private int penalty;

    @OneToMany(mappedBy = "member")
    private List<Joiner> joiners = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<KTXJoiner> ktxJoiners = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<FAVORJoiner> FAVORJoiners = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "reportedMember")
    private List<Report> reportedMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Banner> banners = new ArrayList<>();


    public Member(MemberCreateRequestDTO memberCreateRequestDTO) {
        this.uid = memberCreateRequestDTO.getUid();
        this.email = memberCreateRequestDTO.getEmail();
        this.phone = memberCreateRequestDTO.getPhone();
        this.name = memberCreateRequestDTO.getName();

        /* data validation - uid */
        if(this.uid == null || this.uid.trim().isEmpty())
            throw new MemberUidEmptyException();

        /* data validation - email */
        if(this.email == null || this.email.trim().isEmpty())
            throw new MemberEmailEmptyException();

        /* data validation - phone */
        if(this.phone == null || this.phone.trim().isEmpty())
            throw new MemberPhoneEmptyException();

        /* data validation - name */
        if(this.name == null || this.name.trim().isEmpty())
            throw new MemberNameEmptyException();
    }
}
