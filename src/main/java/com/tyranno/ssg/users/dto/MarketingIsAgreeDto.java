package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.users.domain.MarketingInformation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingIsAgreeDto {

    @NotNull
    private Byte isAgree;

    public MarketingInformation toEntity(MarketingInformation marketingInformation) {
        return MarketingInformation.builder()
                .id(marketingInformation.getId())
                .isAgree(isAgree)
                .users(marketingInformation.getUsers())
                .marketing(marketingInformation.getMarketing())
                .build();
    }
}
