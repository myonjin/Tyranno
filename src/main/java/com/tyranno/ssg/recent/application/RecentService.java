package com.tyranno.ssg.recent.application;

import com.tyranno.ssg.recent.dto.Response.RecentViewedDto;

import java.util.List;

public interface RecentService {
    List<RecentViewedDto> getRecentByUser(String uuid);

    String addRecentByProduct(Long productId, String uuid);
}
