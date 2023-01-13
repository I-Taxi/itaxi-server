package com.itaxi.server.exception;

public class Message {
    public static final String REPORT_NOT_FOUND = "존재하지 않는 리포트입니다.";
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 멤버입니다.";
    public static final String NOTICE_NOT_FOUND = "존재하지 않는 공지입니다.";
    public static final String POST_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String PLACE_NOT_FOUND = "존재하지 않는 장소입니다.";
    public static final String PLACE_NOT_NULL_EXCEPTION = "장소의 이름은 NULL일 수 없습니다";
    public static final String PLACE_CNT_MINUS_EXCEPTION = "장소의 조회수는 0보다 작을 수 없습니다";
    public static final String PLACE_TYPE_MINUS_EXCEPTION = "장소의 타입은 0~4 사이의 값입니다";
    public static final String PLACE_PARAM_NOT_FOUND = "적절하지 않은 PARAM.";

    public static final String BANNER_NOT_FOUND = "존재하지 않는 배너입니다";
    public static final String BANNER_PLACE_NOT_FOUND = "존재하지 않는 배너 장소입니다.";

    /* Member 관련 추가 Exception Message */
    public static final String MEMBER_CREATE_FAILED = "알 수 없는 오류로 인하여 회원가입에 실패하였습니다.";
    public static final String MEMBER_UPDATE_FAILED = "알 수 없는 오류로 인하여 회원정보 수정에 실패하였습니다.";
    public static final String MEMBER_DELETE_FAILED = "알 수 없는 오류로 인하여 회원정보 삭제에 실패하였습니다.";
    public static final String MEMBER_UID_ISNULL = "UID 값이 올바르지 않습니다.";
    public static final String MEMBER_EMAIL_ISNULL = "Email 값이 올바르지 않습니다.";
    public static final String MEMBER_PHONE_ISNULL = "연락처 값이 올바르지 않습니다.";
    public static final String MEMBER_NAME_ISNULL = "사용자 이름 값이 올바르지 않습니다.";

    public static final String MEMBER_DELETE_CONSTRAINT_VIOLATION = "현재 완료되지 않은 그룹이 있습니다. 모든 그룹을 완료하기 전에는 탈퇴할 수 없습니다.";

    public static final String NOTICE_TITLE_EMPTY_EXCEPTION = "공지사항의 제목이 없습니다";
    public static final String NOTICE_CONTENT_EMPTY_EXCEPTION = "공지사항의 내용이 없습니다";
    public static final String NOTICE_DELETED = "이미 삭제된 게시글입니다.";
    public static final String JOINER_DUPLICATE_MEMBER = "이미 해당 post에 존재하는 멤버입니다";
    public static final String POST_MEMBER_FULL = "게시글의 인원이 이미 최대입니다.";
    public static final String POST_TIMEOUT = "이미 입장 및 퇴장 시간이 지난 post입니다.";
    public static final String DEPT_TIME_WRONG = "30분 이상 시간을 미룰 수 없습니다.";
    public static final String CANNOT_CHANGE_DEPT_TIME = "출발시각까지 3분 이하가 남았을 때는 시간을 바꿀 수 없습니다.";
    public static final String POST_TOO_MANY_STOPOVERS = "경유지는 최대 3개까지 선택할 수 있습니다.";

    public static final String BANNER_UID_EMPTY_EXCEPTION = "UID 값이 올바르지 않습니다.";
    public static final String BANNER_BAD_TYPE_EXCEPTION = "배너 타입은 0 또는 1 이어야 합니다.";
    public static final String BANNER_BAD_WEATHER_STATUS_EXCEPTION = "존재하지 않는 날씨 상태입니다.";
    public static final String BANNER_BAD_REPORT_TIME_EXCEPTION = "현재 시간보다 이전에 제보할 수 없습니다.";
    public static final String BANNER_REQUEST_BODY_EXCEPTION = "올바르지 않은 형식으로 데이터가 전달 되었습니다.";
    public static final String BANNER_PLACE_CNT_MINUS_EXCEPTION = "조회수는 0보다 작을 수 없습니다.";
    public static final String BANNER_PLACE_NAME_NULL_EXCEPTION = "장소의 이름은 null값일 수 없습니다.";
    public static final String BANNER_PLACE_REQUEST_BODY_EXCEPTION = "올바르지 않은 형식으로 데이터가 전달 되었습니다.";

    public static final String Joiner_NOT_FOUND = "존재하지 않는 Joiner입니다.";
    public static final String JOINER_NOT_OWNER = "Joiner가 해당 post의 owner가 아닙니다.";
    public static final String KTX_MEMBER_FULL = "KTX 게시글의 인원이 이미 최대입니다.";
    public static final String KTX_TIMEOUT = "이미 입장 및 퇴장 시간이 지난 KTX 게시글입니다.";
    public static final String KTX_NOT_FOUND = "존재하지 않는 KTX 게시글입니다.";
    public static final String KTX_WRONG_CAPACITY = "잘못된 최대인원 값입니다. (1 이상 10 이하)";
    public static final String KTX_SAME_PLACE = "출발 장소와 도착 장소가 같습니다.";
    public static final String KTX_BAD_CNT = "cnt가 음수입니다.";
    public static final String KTX_NAME_NULL = "name이 비어있습니다.";
    public static final String KTX_BAD_DATE = "채팅방 생성 가능 날짜는 현재로부터 3개월입니다.";
    public static final String KTX_REQUEST_BODY_NULL = "올바르지 않은 형식으로 데이터가 전달됐습니다.";


    public static final String FAVOR_JOINER_DUPLICATED = "이미 즐겨찾기로 등록된 장소입니다.";
}
