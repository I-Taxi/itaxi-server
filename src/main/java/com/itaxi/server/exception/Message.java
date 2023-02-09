package com.itaxi.server.exception;

public class Message {
    public static final String BANNER_BAD_PARAM = "데이터 형식이 잘못되었습니다.";
    public static final String BANNER_BAD_REPORT_TIME = "리포트 시간이 잘못되었습니다.";
    public static final String BANNER_BAD_TYPE = "배너 타입이 잘못되었습니다.";
    public static final String BANNER_BAD_WEATHER_STATUS = "날씨 상태가 잘못되었습니다.";
    public static final String BANNER_NO_AUTHORITY = "본 요청에 대한 권한이 없습니다.";
    public static final String BANNER_NOT_FOUND = "존재하지 않는 배너입니다";
    public static final String BANNER_UID_EMPTY = "UID 값이 잘못되었습니다.";

    public static final String BANNER_PLACE_NOT_FOUND = "존재하지 않는 배너 장소입니다.";
    public static final String BANNER_PLACE_CNT_MINUS_EXCEPTION = "조회수가 잘못되었습니다.";
    public static final String BANNER_PLACE_NAME_NULL_EXCEPTION = "장소의 이름이 잘못되었습니다.";

    public static final String FAVOR_DUPLICATE = "즐겨찾기가 중복됩니다.";
    public static final String FAVOR_NO_AUTHORITY = "본 요청에 대한 권한이 없습니다.";

    public static final String JOINER_DUPLICATE_MEMBER = "이미 해당 채팅방에 존재하는 멤버입니다";
    public static final String Joiner_NOT_FOUND = "채팅방에 존재하지 않는 유저입니다.";

    public static final String KTX_BAD_CAPACITY = "최대인원이 잘못되었습니다";
    public static final String KTX_BAD_CNT = "count가 잘못되었습니다.";
    public static final String KTX_BAD_DATE = "출발 시간이 잘못되었습니다.";
    public static final String KTX_BAD_SALE = "할인률이 잘못되었습니다.";
    public static final String KTX_DUPLICATE_PLACE = "출발 장소와 도착 장소는 같을 수 없습니다.";
    public static final String KTX_MEMBER_FULL = "KTX 채팅방의 인원이 이미 최대입니다.";
    public static final String KTX_NAME_EMPTY = "이름이 잘못되었습니다.";
    public static final String KTX_NO_AUTHORITY = "본 요청에 대한 권한이 없습니다.";
    public static final String KTX_NOT_FOUND = "존재하지 않는 KTX 채팅방입니다.";
    public static final String KTX_REQUEST_BODY_EMPTY= "올바르지 않은 형식으로 데이터가 전달됐습니다.";
    public static final String KTX_TIME_OVER = "출발 시간이 잘못되었습니다.";

    public static final String MEMBER_CONSTRANINT_VIOLATION = "다른 방에 입장해있으면 탈퇴할 수 없습니다.";
    public static final String MEMBER_CREATE_FAILED = "알 수 없는 오류로 인하여 회원가입에 실패하였습니다.";
    public static final String MEMBER_DELETE_FAILED = "알 수 없는 오류로 인하여 회원정보 삭제에 실패하였습니다.";
    public static final String MEMBER_ADMIN_DUPLICATE = "이름이 중복됩니다.";
    public static final String MEMBER_EMAIL_ISNULL = "Email 값이 올바르지 않습니다.";
    public static final String MEMBER_NAME_ISNULL = "사용자 이름 값이 올바르지 않습니다.";
    public static final String MEMBER_NOT_ADMIN = "사용자에게 권한이 없습니다.";
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 멤버입니다.";
    public static final String MEMBER_PHONE_ISNULL = "연락처 값이 올바르지 않습니다.";
    public static final String MEMBER_UID_ISNULL = "UID 값이 올바르지 않습니다.";
    public static final String MEMBER_UPDATE_FAILED = "알 수 없는 오류로 인하여 회원정보 수정에 실패하였습니다.";




    // TODO: 이어서 하기
    public static final String REPORT_NOT_FOUND = "존재하지 않는 리포트입니다.";
    public static final String NOTICE_NOT_FOUND = "존재하지 않는 공지입니다.";
    public static final String POST_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String PLACE_NOT_FOUND = "존재하지 않는 장소입니다.";
    public static final String PLACE_NOT_NULL_EXCEPTION = "장소 이름이 잘못되었습니다.";
    public static final String PLACE_CNT_MINUS_EXCEPTION = "장소 조회수가 잘못되었습니다.";
    public static final String PLACE_TYPE_MINUS_EXCEPTION = "장소의 타입은 0~4 사이의 값입니다";
    public static final String PLACE_FIND_TYPE_EXCEPTION = "FindType이 잘못되었습니다.";
    public static final String PLACE_PARAM_NOT_FOUND = "적절하지 않은 PARAM.";
    public static final String HISTORY_BAD_TYPE = "잘못된 타입입니다.";

    public static final String MEMBER_NOT_WRITER = "작성자가 아닙니다.";

    public static final String NOTICE_TITLE_EMPTY_EXCEPTION = "공지사항의 제목이 없습니다";
    public static final String NOTICE_CONTENT_EMPTY_EXCEPTION = "공지사항의 내용이 없습니다";
    public static final String NOTICE_DELETED = "이미 삭제된 게시글입니다.";
    public static final String NOTICE_BAD_TYPE_EXCEPTION = "지원하지 않는 공지 형식입니다.";
    public static final String NOTICE_TIME_EMPTY_EXCEPTION = "해당 공지 형식은 시간의 입력이 필요합니다.";

    public static final String POST_MEMBER_FULL = "게시글의 인원이 이미 최대입니다.";
    public static final String POST_TIMEOVER = "입장 및 퇴장 시간이 지난 채팅방입니다.";
    public static final String DEPT_TIME_WRONG = "30분 이상 시간을 미룰 수 없습니다.";
    public static final String CANNOT_CHANGE_DEPT_TIME = "출발시각까지 3분 이하가 남았을 때는 시간을 바꿀 수 없습니다.";
    public static final String POST_TOO_MANY_STOPOVERS = "경유지는 최대 3개까지 선택할 수 있습니다.";
    public static final String POST_NO_AUTHORITY_TO_GET = "해당 채팅방에 참가되어 있지 않아 접근 권한이 존재하지 않습니다.";



    public static final String JOINER_NOT_OWNER = "유저가 해당 채팅방의 주인이 아닙니다.";
}

