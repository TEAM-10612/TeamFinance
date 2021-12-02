package com.financialboard.service;

import com.financialboard.dto.FollowDto;
import com.financialboard.exception.follow.ExistFollowException;
import com.financialboard.exception.follow.NotFoundFollowException;
import com.financialboard.model.user.User;
import com.financialboard.repository.FollowRepository;
import com.financialboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.financialboard.dto.FollowDto.*;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public void follow(Long fromUserId, Long toUserId){
        if(followRepository.findFollowByFromIdAndToUserId(fromUserId, toUserId) != null)
            throw new ExistFollowException();

        followRepository.follow(fromUserId, toUserId);
    }


    @Transactional
    public void unFollow(Long fromUserId,Long toUserId){
        if (followRepository.findFollowByFromIdAndToUserId(fromUserId,toUserId) == null)
            throw new NotFoundFollowException();
        followRepository.unFollow(fromUserId, toUserId);
    }
//
//    @Transactional(readOnly = true)
//    public List<FollowerInfo> getFollower(Long profileId,Long loginId){
//        return
//    }
}
