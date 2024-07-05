package com.tyranno.ssg.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(true, 200, "요청에 성공했습니다."),
    //
//    /**
//     * 400 : security 에러
//     */
//    WRONG_JWT_TOKEN(false, 401, "다시 로그인 해주세요"),
//
//    /**
//     * 900: 기타 에러
//     */
    INTERNAL_SERVER_ERROR(false, 900, "Internal server error"),
    //
//    /**
//     * 1000 : Order Service Error
//     */
//    ALREADY_PAID_ORDER_ID(false, 1000, "이미 결제된 주문번호입니다"),
//    DOSE_NOT_EXIST_PAYMENT(false, 1001, "결제내역이 존재하지 않습니다"),
//    CANCELED_AMOUNT_EXCEEDED(false, 1002, "취소 금액 한도를 초과하였습니다"),
//    PAYMENT_DATA_TRANSFER_FAILED(false, 1003, "결제 정산 정보 전송에 실패하였습니다"),
//
//    /**
//     * 2000 : USERS Service Error
//     */
//    // Token, Code
//    TOKEN_EXPIRED(false, 2001, "토큰이 만료되었습니다."),
    TOKEN_NOT_VALID(false, 2002, "토큰이 유효하지 않습니다."),
//    TOKEN_NULL(false, 2003, "토큰이 존재하지 않습니다."),
//    JWT_CREATE_FAILED(false, 2004, "토큰 생성에 실패했습니다."),
//    JWT_VALID_FAILED(false, 2005, "토큰 검증에 실패했습니다."),
//    EXPIRED_AUTH_CODE(false, 2006, "인증번호가 만료되었거나 존재하지 않는 유저입니다."),
//    WRONG_AUTH_CODE(false, 2007, "인증번호가 일치하지 않습니다."),

    //
//    // USERS
    DUPLICATE_ID(false, 2100, "사용중인 아이디입니다."),
    DUPLICATE_EMAIL(false, 2101, "사용중인 이메일입니다."),
    DUPLICATED_USERS(false, 2102, "이미 가입된 유저입니다."),
//    MASSAGE_SEND_FAILED(false, 2102, "인증번호 전송에 실패했습니다."),
//    MASSAGE_VALID_FAILED(false, 2103, "인증번호가 일치하지 않습니다."),
    FAILED_TO_LOGIN(false, 2104, "아이디 또는 패스워드를 다시 확인하세요."),
    //    WITHDRAWAL_USERS(false, 2105, "탈퇴한 회원입니다."),
    NO_EXIST_USERS(false, 2106, "존재하지 않는 유저 정보입니다."),
    NO_SIGNUP(false, 2107, "회원가입 이력이 존재하지 않습니다."),
    NO_REGISTER(false, 2108, "통합 회원이 아닙니다."),
    ONLY_FOR_MEMBERS(false, 2109, "회원만 사용가능한 서비스입니다."),
    //    USERS_STATUS_IS_NOT_FOUND(false, 2107, "존재하지 않는 유저 상태입니다."),
    //    PASSWORD_SAME_FAILED(false, 2108, "현재 사용중인 비밀번호 입니다."),
//    PASSWORD_CONTAIN_NUM_FAILED(false, 2109, "휴대폰 번호를 포함한 비밀번호 입니다."),
//    PASSWORD_CONTAIN_EMAIL_FAILED(false, 2110, "이메일이 포함된 비밀번호 입니다."),

    // OAuth
    NO_EXIST_OAUTH(false, 2200, "존재하지 않는 소셜 회원입니다."),
    DUPLICATE_OAUTH(false, 2201, "이미 가입된 소셜 회원입니다."),
    NO_EXIST_USERS_TYPE(false, 2201, "통합회원 정보가 잘못되어 있습니다. 고객센터에 문의하세요."),

//
//    // Marketing
    NO_EXIST_MARKETING(false, 2300, "마케팅동의여부가 조회되지 않습니다."),
    NO_FORMATING(false, 2301, "마케팅동의 형식에 맞지 않습니다."),

    // Delivery
    NO_EXIST_DELIVERY(false, 3000, "존재하지 않는 배송지입니다."),

    // Option
    NO_EXIST_OPTION(false, 4000, "존재하지 않는 옵션입니다."),

    DUPLICATE_OPTION(false, 4001, "이미 존재하는 옵션입니니다."),

    ALREADY_EXIST_OPTION(false, 4001, "이미 존재하는 옵션입니니다."),

    NO_SELECTED_OPTION(false, 4003, "선택된 옵션이 없습니다."),

    // Cart
    NO_EXIST_CART(false, 5000, "존재하지 않는 장바구니입니다."),

//    NO_DATA(false, 6001, "존재하지 않는 정보입니다"),
//    ALREADY_ADDED_PRODUCT(false, 6002, "이미 장바구니에 존재하는 상품입니다"),
//    ALREADY_ADDED_WISH_PRODUCT(false, 6003, "이미 찜한 상품입니다");

    // Product
    NO_EXIST_PRODUCT(false, 7000, "존재하지 않는 상품입니다."),
    ALREADY_EXIST_PRODUCT(false, 7001, "이미 존재하는 상품입니니다."),
    NO_EXIST_PRODUCTTHUM(false, 7100, "존재하지 않는 상품썸네일입니다."),
    NO_EXIST_DISCOUNT(false, 7200, "존재하지 않는 할인율입니다."),
    NO_EXIST_IMAGE(false, 7300, "이미지가 존재하지 않습니다."),


    // Vendor
    NO_EXIST_VENDORPRODUCT(false, 8000, "존재하지 않는 판매자상품입니다."),
    NO_EXIST_VENDOR(false, 8100, "존재하지 않는 판매자입니다."),

    // 문자인증
    NO_MATCH_CERTIFICATION_NUMBER(false, 9000, "인증번호가 일치하지 않습니다."),

    // Order
    NO_EXIST_ORDER_LIST(false, 10000, "존재하지 않는 주문내역입니다."),
    NO_EXIST_ORDERS(false, 10100, "이 상품을 주문하지 않았습니다."),
    NO_EXIST_ORDERPRODUCT(false, 10200, "이 상품을 구매한 이력이 없습니다."),
  
    // payment
    PAY_CANCEL(false, 11000, "결제가 취소되었습니다."),
    PAY_FAILED(false, 11001, "결제가 실패하였습니다."),
    NO_EXIST_PAYMENT(false, 11002, "존재하지 않는 구매내역입니다."),

    // Review
    USER_HAS_NOT_PURCHASED_THE_PRODUCT(false, 12000, "상품을 구매하지 않은 사용자입니다."),
    FAILED_TO_REVIEW(false, 12100, "리뷰 저장에 실패했습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

}
