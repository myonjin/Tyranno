package com.tyranno.ssg.like.application;

import com.tyranno.ssg.like.dto.Response.LikeListDto;

public interface LikeService {
    boolean modifyLike(Long productId, String uuid);
    int getLike(Long productId, String uuid);
    LikeListDto getLikeList(String uuid, Integer page);
}
