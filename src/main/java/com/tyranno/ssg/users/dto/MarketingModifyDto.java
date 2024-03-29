package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.users.domain.Marketing;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingModifyDto {
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
