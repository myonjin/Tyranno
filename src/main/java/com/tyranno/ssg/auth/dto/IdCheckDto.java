package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.config.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdCheckDto {

    @NotBlank(message = "사용할 아이디를 입력하세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "아이디는 영어 또는 숫자로 6 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String loginId;
}
