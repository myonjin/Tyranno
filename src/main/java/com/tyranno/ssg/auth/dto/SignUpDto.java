package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.config.ValidationGroups;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @NotBlank(message = "아이디를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "아이디는 영어 또는 숫자로 6 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
            @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String deliveryBase;

    @NotBlank
    private String deliveryDetail;

    @NotBlank
    private int zipCode;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$",
            message = "휴대폰 번호 형식이 올바르지 않습니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String phoneNumber;

    @NotBlank(message = "이메일을 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "올바르지 않은 이메일 형식입니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String email;

    @NotBlank
    private Byte gender;

    @NotBlank
    private LocalDate birth;

    @NotBlank
    private Byte shinsegaeMarketingAgree;

    @NotBlank
    private Byte shinsegaeOptionAgree;

    @NotBlank
    private Byte ssgMarketingAgree;

    public Users toUsersEntity() {
        return Users.builder()
                .loginId(loginId)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .status(0) // 활동중
                .isIntegrated((byte) 1) // 통합회원 여부 true
                .uuid(UUID.randomUUID().toString())
                .build();
    }

    public Delivery toDeliveryEntity(Users users) {
        return Delivery.builder()
                .users(users)
                .isBaseDelivery((byte) 11)
                .deliveryName(name)
                .zipCode(zipCode)
                .deliveryBase(deliveryBase)
                .deliveryDetail(deliveryDetail)
                .receiverName(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
