package com.itaxi.server.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.post.domain.Joiner;

import lombok.Getter;

@Entity
@Getter
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

}
