package com.tyranno.ssg.like.application;

public interface LikeService {
    boolean modifyLike(Long productId, String uuid);
    int getLike(Long productId, String uuid);
}
