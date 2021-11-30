package com.financialboard.repository;

import com.financialboard.model.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    Follow findFollowByFromIdAndToUserId(Long from_user_id,Long to_user_id);

    //follower Count
    @Query(value = "SELECT COUNT(*) FROM follow WHERE to_user_id = :profileId", nativeQuery = true)
    int findFollowCounterById(Long profiledId);


    //following Count
    @Query(value = "SELECT COUNT(*) FROM follow WHERE from_user_id = :profileId", nativeQuery = true)
    int findFollowingCountById(Long profileId);

    @Modifying
    @Query(value = "INSERT INTO follow(from_user_id, to_user_id) VALUES(:fromId, :toId)", nativeQuery = true)
    void follow(Long fromId, Long toId);

    @Modifying
    @Query(value = "DELETE FROM follow WHERE from_user_id = :fromId AND to_user_id = :toId", nativeQuery = true)
    void unFollow(Long fromId, Long toId);
}
