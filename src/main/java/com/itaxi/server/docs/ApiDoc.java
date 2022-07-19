package com.itaxi.server.docs;

public class ApiDoc {
    public static final String TEST = "테스트API";
    public static final String CREATE_NOTICE = "공지사항 등록";
    public static final String READ_ALL_NOTICES = "공지사항 다건 조회";
    public static final String READ_NOTICE = "공지사항 단일 조회";
    public static final String UPDATE_NOTICE = "공지사항 수정";
    public static final String DELETE_NOTICE = "공지사항 삭제";

    /* Member 관련 API 명세 */
    public static final String MEMBER_CREATE = "회원가입";
    public static final String MEMBER_READ = "회원정보 단일 조회";
    public static final String MEMBER_LOGIN = "로그인";
    public static final String MEMBER_UPDATE = "회원정보 업데이트";
    public static final String MEMBER_DELETE = "회원 탈퇴";
    public static final String JOIN_POST = "채팅방 입장";
    public static final String EXIT_POST = "채팅방 나가기";

    /* Post 관련 API 명세 */
    public static final String POST_HISTORY = "탑승내역 조회";
    public static final String POST_HISTORY_DETAIL = "탑승내역 상세조회";
    public static final String POST_CREATE = "채팅방 생성";
    public static final String POST_READ = "날짜별 채팅방 조회";

    /* Place 관련 API 명세 */
    public static final String PLACE_CREATE = "장소 생성";
    public static final String PLACE_READ = "장소 조회";
    public static final String PLACE_UPDATE_COUNT = "장소 조회수 증가";
    public static final String PLACE_UPDATE = "장소 수정";
    public static final String PLACE_DELETE = "장소 삭제";
}
