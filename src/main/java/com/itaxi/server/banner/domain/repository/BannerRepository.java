package com.itaxi.server.banner.domain.repository;

import com.itaxi.server.banner.domain.Banner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
