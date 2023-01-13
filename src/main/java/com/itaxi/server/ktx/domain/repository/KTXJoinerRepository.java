package com.itaxi.server.ktx.domain.repository;

import com.itaxi.server.ktx.domain.KTX;
import com.itaxi.server.ktx.domain.KTXJoiner;
import com.itaxi.server.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KTXJoinerRepository extends JpaRepository<KTXJoiner, Long> {
    Optional<KTXJoiner> findKtxJoinerByKtxAndMember(KTX ktx, Member member);

    List<KTXJoiner> findKtxJoinerByKtx(KTX ktx);
}
