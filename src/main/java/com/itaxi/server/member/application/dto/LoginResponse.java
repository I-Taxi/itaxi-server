package com.itaxi.server.member.application.dto;

public interface LoginResponse {
    Long getId();
    String getUid();
    String getEmail();
    String getPhone();
    String getName();
    String getBank();
    String getBankAddress();
    String getBankOwner();

}
