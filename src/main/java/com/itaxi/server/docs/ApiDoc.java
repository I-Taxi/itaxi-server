package com.itaxi.server.docs;

public class ApiDoc {
    public static final String TEST = "테스트API";
    public static final String CREATE_NOTICE = "공지사항 등록";
    public static final String READ_ALL_NOTICES = "공지사항 다건 조회";
    public static final String READ_NOTICE = "공지사항 단일 조회";
    public static final String UPDATE_NOTICE = "공지사항 수정";
    public static final String DELETE_NOTICE = "공지사항 삭제";
    public static final String UPDATE_NOTICE_VIEWCNT = "공지사항 조회수 증가";

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

    /* KTX Place 관련 API 명세 */
    public static final String KTX_PLACE_CREATE = "KTX 장소 생성";
    public static final String KTX_PLACE_READ = "KTX 장소 조회";
    public static final String KTX_PLACE_UPDATE_COUNT = "KTX 장소 조회수 증가";
    public static final String KTX_PLACE_UPDATE = "KTX 장소 수정";
    public static final String KTX_PLACE_DELETE = "KTX 장소 삭제";

    /* KTX 관련 API 명세 */
    public static final String KTX_CREATE = "KTX 채팅방 생성";
    public static final String KTX_READ = "KTX 채팅방 조회";
    public static final String KTX_HISTORY = "KTX 탑승내역 조회";
    public static final String KTX_HISTORY_DETAIL = "KTX 탑승내역 상세조회";
    public static final String KTX_STOP = "KTX 모집 중단";
    public static final String JOIN_KTX = "KTX 채팅방 입장";
    public static final String EXIT_KTX = "KTX 채팅방 나가기";

    /* BANNER 관련 API 명세 */
    public static final String BANNER_CREATE = "BANNER 생성";
    public static final String BANNER_READ = "BANNER 단일 조회";
    public static final String BANNER_READ_ALL = "BANNER 다건 조회";
    public static final String BANNER_READ_RECENT_ALL = "BANNER 최근 조회";
    public static final String BANNER_UPDATE = "BANNER 수정";
    public static final String BANNER_DELETE = "BANNER 삭제";

    public static final String BANNER_PLACE_CREATE = "BANNER 장소 생성";
    public static final String BANNER_PLACE_READ = "BANNER 장소 조회";
    public static final String BANNER_PLACE_UPDATE_COUNT = "BANNER 장소 조회수 증가";
    public static final String BANNER_PLACE_UPDATE = "BANNER 장소 수정";
    public static final String BANNER_PLACE_DELETE = "BANNER 장소 삭제";

}
