package com.itaxi.server.exception;

public class Message {
    public static final String REPORT_NOT_FOUND = "존재하지 않는 리포트입니다.";
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 멤버입니다.";
    public static final String NOTICE_NOT_FOUND = "존재하지 않는 공지입니다.";
    public static final String POST_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String PLACE_NOT_FOUND = "존재하지 않는 장소입니다.";
    public static final String PLACE_PARAM_NOT_FOUND = "적절하지 않은 PARAM.";

    /* Member 관련 추가 Exception Message */
    public static final String MEMBER_CREATE_FAILED = "알 수 없는 오류로 인하여 회원가입에 실패하였습니다.";
    public static final String MEMBER_UPDATE_FAILED = "알 수 없는 오류로 인하여 회원정보 수정에 실패하였습니다.";
    public static final String MEMBER_DELETE_FAILED = "알 수 없는 오류로 인하여 회원정보 삭제에 실패하였습니다.";
    public static final String MEMBER_UID_ISNULL = "UID 값이 올바르지 않습니다.";
    public static final String MEMBER_EMAIL_ISNULL = "Email 값이 올바르지 않습니다.";
    public static final String MEMBER_PHONE_ISNULL = "연락처 값이 올바르지 않습니다.";
    public static final String MEMBER_NAME_ISNULL = "사용자 이름 값이 올바르지 않습니다.";

    public static final String NOTICE_TITLE_EMPTY_EXCEPTION = "공지사항의 제목이 없습니다";
    public static final String NOTICE_CONTENT_EMPTY_EXCEPTION = "공지사항의 내용이 없습니다";
    public static final String NOTICE_DELETED = "이미 삭제된 게시글입니다.";
    public static final String JOINER_DUPLICATE_MEMBER = "이미 해당 post에 존재하는 멤버입니다";
    public static final String POST_MEMBER_FULL = "게시글의 인원이 이미 최대입니다.";
    public static final String POST_TIMEOUT = "이미 입장 및 퇴장 시간이 지난 post입니다.";
    public static String Joiner_NOT_FOUND = "존재하지 않는 Joiner입니다.";
}
