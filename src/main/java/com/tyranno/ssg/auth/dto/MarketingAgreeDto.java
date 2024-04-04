package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.users.domain.Marketing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingAgreeDto {

    private Byte shinsegaeMarketingAgree;

    private Byte shinsegaeOptionAgree;

    private Byte ssgMarketingAgree;

}
