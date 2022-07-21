package com.itaxi.server.exception;

public class Message {
    public static final String REPORT_NOT_FOUND = "존재하지 않는 리포트입니다.";
    public static final String MEMBER_NOT_FOUND = "존재하지 않는 멤버입니다.";
    public static final String NOTICE_NOT_FOUND = "존재하지 않는 공지입니다.";
    public static final String POST_NOT_FOUND = "존재하지 않는 게시글입니다.";

    public static final String NOTICE_TITLE_EMPTY_EXCEPTION = "공지사항의 제목이 없습니다";
    public static final String NOTICE_CONTENT_EMPTY_EXCEPTION = "공지사항의 내용이 없습니다";
    public static final String NOTICE_DELETED = "이미 삭제된 게시글입니다.";
    public static final String JOINER_DUPLICATE_MEMBER = "이미 해당 post에 존재하는 멤버입니다";
    public static final String POST_MEMBER_FULL = "게시글의 인원이 이미 최대입니다.";
    public static String Joiner_NOT_FOUND = "존재하지 않는 Joiner입니다.";
}
