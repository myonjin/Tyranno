package com.tyranno.ssg.recent.application;

import com.tyranno.ssg.recent.dto.RecentViewedDto;

public interface RecentService {
    RecentViewedDto getRecentByUser(String uuid);
}
