package com.itaxi.server.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.post.domain.Joiner;
import com.itaxi.server.report.domain.Report;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String email;

    private String phone;

    private String name;

    private String bank;

    private int penalty;

    private String bankAddress;

    @OneToMany(mappedBy = "member")
    private List<Joiner> joiners = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Report> reports = new ArrayList<>();
}
