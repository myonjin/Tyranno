package com.tyranno.ssg.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class MarketingDto {
    private Byte shinsegaeMarketingAgree;

    private Byte shinsegaeOptionAgree;

    private Byte ssgMarketingAgree;
}
