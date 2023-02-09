package com.itaxi.server.banner.application;

import com.itaxi.server.banner.application.dto.BannerCreateDto;
import com.itaxi.server.banner.application.dto.BannerCreateEntityDto;
import com.itaxi.server.banner.application.dto.BannerDeleteDto;
import com.itaxi.server.banner.application.dto.BannerUpdateDto;
import com.itaxi.server.banner.domain.Banner;
import com.itaxi.server.banner.domain.repository.BannerRepository;
import com.itaxi.server.banner.presentation.reponse.*;
import com.itaxi.server.cheaker.AdminChecker;
import com.itaxi.server.exception.banner.*;
import com.itaxi.server.exception.member.MemberNotAdminException;
import com.itaxi.server.exception.member.MemberNotFoundException;
import com.itaxi.server.exception.notice.NoticeNotFoundException;
import com.itaxi.server.exception.place.PlaceNotFoundException;
import com.itaxi.server.member.domain.Member;
import com.itaxi.server.member.domain.repository.MemberRepository;
import com.itaxi.server.notice.domain.Notice;
import com.itaxi.server.notice.domain.repository.NoticeRepository;
import com.itaxi.server.place.domain.Place;
import com.itaxi.server.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerService {
    private final MemberRepository memberRepository;
    private final BannerRepository bannerRepository;
    private final PlaceRepository placeRepository;
    private final NoticeRepository noticeRepository;
    private final AdminChecker adminChecker;

    String[] weather={"폭우","침수","폭설","공사"};
    public String[] notice_type={"일반","긴급","정전","점검"};
    @Transactional
    public BannerCreateResponse createBanner(BannerCreateDto bannerCreateDto){
        int gapBetweenReportAndNow = 30;

        LocalDateTime currentDate = LocalDateTime.now().minusMinutes(gapBetweenReportAndNow);

        if (bannerCreateDto.getWeatherStatus() < 0 || bannerCreateDto.getWeatherStatus() > weather.length-1) throw
                new BannerBadWeatherStatusException();

        if (bannerCreateDto.getUid() == null || bannerCreateDto.getUid() == "" || bannerCreateDto.getUid().equals(" ")) throw
                new BannerUidEmptyException();

        if (bannerCreateDto.getBannerType() != 0) throw
                new BannerBadTypeException();

        if (currentDate.isAfter(bannerCreateDto.getReportAt())) throw
                new BannerBadReportTimeException();

        Optional<Place> placeDepart = placeRepository.findById(bannerCreateDto.getDepId());
        Optional<Place> placeDest = placeRepository.findById(bannerCreateDto.getDesId());
        Optional<Member> member = memberRepository.findMemberByUid(bannerCreateDto.getUid());
        if (member.get().isDeleted())
            throw new MemberNotFoundException();

        if (!(placeDepart.isPresent() && placeDest.isPresent())) throw
                new PlaceNotFoundException();
        if (!(member.isPresent())) throw
                new MemberNotFoundException();
        if (!(bannerCreateDto.getWeatherStatus() >= 0 && bannerCreateDto.getWeatherStatus() <= 3)) throw
                new BannerBadWeatherStatusException();

        BannerCreateEntityDto bannerCreateEntityDto =
                new BannerCreateEntityDto(
                    member.get(),bannerCreateDto.getWeatherStatus(),
                    placeDepart.get(),placeDest.get(),bannerCreateDto.getReportAt(),
                    bannerCreateDto.getBannerType()
                );

        Banner saveBanner = bannerRepository.save(new Banner(bannerCreateEntityDto));

        BannerCreateResponse bannerCreateResponse = new BannerCreateResponse(
                saveBanner.getId(),member.get().getName(),saveBanner.getMember().getUid(),
                saveBanner.getWeatherStatus(), saveBanner.getDeparture().getId(),
                saveBanner.getDestination().getId(),saveBanner.getReportAt(),
                saveBanner.getBannerType());

        return bannerCreateResponse;
    }

    @Transactional
    public BannerUpdateResponse updateBanner(Long bannerId,BannerUpdateDto bannerUpdateDto){

        Optional<Place> placeDepart = placeRepository.findById(bannerUpdateDto.getDepId());
        Optional<Place> placeDest = placeRepository.findById(bannerUpdateDto.getDesId());

        if(!(placeDepart.isPresent() && placeDest.isPresent())) throw
                new PlaceNotFoundException();

        if(bannerUpdateDto.getWeatherStatus()<0 || bannerUpdateDto.getWeatherStatus()>weather.length-1) throw
                new BannerBadWeatherStatusException();

        Optional<Banner> banner = bannerRepository.findById(bannerId);

        if(!(banner.isPresent())) throw
                new BannerNotFoundException();


        if (! banner.get().getMember().getUid().equals(bannerUpdateDto.getUid())) throw
                new BannerNoAuthorityException();

        Banner bannerInfo = banner.get();
        bannerInfo.setWeatherStatus(bannerUpdateDto.getWeatherStatus());
        bannerInfo.setDeparture(placeDepart.get());
        bannerInfo.setDestination(placeDest.get());
        bannerRepository.save(bannerInfo);

        BannerUpdateResponse result = new BannerUpdateResponse(
                bannerInfo.getWeatherStatus(),bannerInfo.getDeparture().getId(),
                bannerInfo.getDestination().getId());


        return result;
    }

    @Transactional
    public BannerReadResponse readBanner(Long bannerId){

        Optional<Banner> banner = bannerRepository.findById(bannerId);

        if(!banner.isPresent())
            throw new BannerNotFoundException();
        ;
        Optional<Member> member = memberRepository.findMemberByUid(banner.get().getMember().getUid());
        if (member.get().isDeleted())
            throw new MemberNotFoundException();
        BannerReadResponse response = null;

        if(member.isPresent()){
            Banner bannerInfo = banner.get();
            response = new BannerReadResponse(
                    bannerInfo.getId(), member.get().getName(), bannerInfo.getWeatherStatus(),
                    bannerInfo.getDeparture().getId(), bannerInfo.getDestination().getId(),
                    bannerInfo.getCreatedAt(),bannerInfo.getUpdateAt());
        }
        else {throw new MemberNotFoundException();}



        return response;
    }

    @Transactional
    public List<BannerReadAllResponse> readAllBanners(String uid){

        if(!adminChecker.isAdmin(uid)) {
            throw new MemberNotAdminException();
        }

        List<BannerReadAllResponse> result = new ArrayList<>();
        for(Banner banner : bannerRepository.findAll()){
            if(banner == null) throw new BannerNotFoundException();
            Optional<Member> member = memberRepository.findMemberByUid(banner.getMember().getUid());
            if(member.get().isDeleted())
                throw new MemberNotFoundException();

                if(member.isPresent()){
                    result.add(0,new BannerReadAllResponse(banner.getId(), member.get().getName(),
                            banner.getWeatherStatus(), banner.getDeparture().getId(),
                            banner.getDestination().getId(), banner.getReportAt()));
                }
                else {throw new MemberNotFoundException();}
        }

        if(result.size() == 0)
            throw new BannerNotFoundException();
        return result;
    }

    @Transactional
    public List<BannerReadAllRecentResponse> readAllRecentBanners(LocalDateTime time){
        List<BannerReadAllRecentResponse> result = new ArrayList<>();

        String output = "";
        for(Banner banner : bannerRepository.findAll()){
            Optional<Place> placeDepart = placeRepository.findById(banner.getDeparture().getId());
            Optional<Place> placeDest = placeRepository.findById(banner.getDestination().getId());

            output = "제보자: ";
            if(banner == null) throw new BannerNotFoundException();
            Optional<Member> member = memberRepository.findMemberByUid(banner.getMember().getUid());
            if(member.get().isDeleted())
                throw new MemberNotFoundException();

            if(member.isPresent()){
              output = output.concat(member.get().getName());
            }
            else {throw new MemberNotFoundException();}

            if(time.isAfter(banner.getReportAt()) ) {
                output= output.concat(" , 제보시각 :");
                output = output.concat(banner.getReportAt().format(DateTimeFormatter.ofPattern("a HH시 mm분")));
                output = output.concat(" , 장소 : ");
            }
            else{continue;}

            if(placeDepart.isPresent() && placeDest.isPresent()){
                if(banner.getDestination() == banner.getDeparture()){
                    output = output.concat("특정 장소 ");
                    output = output.concat(placeDepart.get().getName());
                }
                else{
                    output = output.concat(placeDepart.get().getName());
                    output = output.concat("에서 ");
                    output = output.concat(placeDest.get().getName());
                    output = output.concat("로 가는 도로");
                }
            }
            else{
                throw new PlaceNotFoundException();
            }


            if(0<=banner.getWeatherStatus() && banner.getWeatherStatus()<= weather.length-1){
                output = output.concat(" , 요인 : ");
                output = output.concat(weather[banner.getWeatherStatus()]);
            }
            else {throw new BannerBadWeatherStatusException();}

            result.add(0,new BannerReadAllRecentResponse(banner.getBannerType(),output));
        }

        for(Notice notice : noticeRepository.findAll()){
            output = "작성자: 관리자 ,";
            if(notice!=null){
                if(notice.getNoticeType()==2||notice.getNoticeType()==3){
                    output = output.concat((notice.getStartTime().format(DateTimeFormatter.ofPattern("a HH시 mm분"))));
                    output = output.concat("부터 ");
                    output = output.concat((notice.getEndTime().format(DateTimeFormatter.ofPattern("a HH시 mm분"))));
                    output = output.concat("까지 진행되는 ");
                    output = output.concat(notice_type[notice.getNoticeType()]);
                    output = output.concat("공지 생성, ");
                    output = output.concat( Long.toString(notice.getId()));
                    output = output.concat("번 글 확인");
                    result.add(0,new BannerReadAllRecentResponse(1,output));
                    continue;
                }
                else{
                    if(time.minusHours(1).isAfter(notice.getCreatedAt())){
                        output = output.concat(notice_type[notice.getNoticeType()]);
                        output = output.concat("공지 생성, ");
                        output = output.concat( Long.toString(notice.getId()));
                        output = output.concat("번 글 확인");
                        result.add(0,new BannerReadAllRecentResponse(1,output));
                    }
                }

            }
            else throw new NoticeNotFoundException();

        }

        return result;
    }

    @Transactional
    public String deleteBanner(Long bannerId, BannerDeleteDto bannerDeleteDto){
        Optional<Banner> banner = bannerRepository.findById(bannerId);
        if(!(banner.get().getMember().getUid().equals(bannerDeleteDto.getUid()))) throw
            new BannerNoAuthorityException();
        if(banner.isPresent()){
            Banner bannerInfo = banner.get();
            bannerInfo.setDeleted(true);
            bannerRepository.save(bannerInfo);
        }
        else{
            throw new BannerNotFoundException();
        }
        return "Success";
    }
}
