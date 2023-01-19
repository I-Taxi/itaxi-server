package com.itaxi.server.ktx.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.ktxPlace.application.dto.KTXPlaceResponse;
import com.itaxi.server.ktxPlace.domain.KTXPlace;
import com.itaxi.server.ktx.application.dto.KTXInfoResponse;
import com.itaxi.server.ktx.application.dto.KTXJoinerInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KTX extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private KTXPlace departure;

    @ManyToOne(fetch = FetchType.EAGER)
    private KTXPlace destination;

    @NotNull
    private LocalDateTime deptTime;

    @NotNull
    private int capacity;

    private int status;

    @OneToMany(mappedBy = "ktx")
    private List<KTXJoiner> joiners = new ArrayList<>();

    @Builder
    public KTX(KTXPlace departure, KTXPlace destination, LocalDateTime deptTime, int capacity, int status) {
        this.departure = departure;
        this.destination = destination;
        this.deptTime = deptTime;
        this.capacity = capacity;
        this.status = status;
    }

    public KTXInfoResponse toKTXInfoResponse() {
        KTXPlaceResponse deptResponse = new KTXPlaceResponse(departure.getId(), departure.getName(), departure.getCnt());
        KTXPlaceResponse destResponse = new KTXPlaceResponse(destination.getId(), destination.getName(), destination.getCnt());

        List<KTXJoinerInfo> joinerResponse = new ArrayList<>();
        for (KTXJoiner joiner : joiners) {
            joinerResponse.add(new KTXJoinerInfo(joiner));
        }

        return new KTXInfoResponse(id, deptResponse, destResponse, deptTime, capacity, status, joinerResponse);
    }
}
