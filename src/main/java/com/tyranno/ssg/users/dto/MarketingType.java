package com.tyranno.ssg.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MarketingType {
    SHINSEGAE(1L),
    SHINSEGAE_OPTION(2L),
    SSG(3L);

    private final Long id;
}
