package com.financialboard.repository.admin;

import com.financialboard.dto.UserDto;
import com.financialboard.dto.UserDto.UserListInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepository {
    Page<UserListInfo> searchByUser(UserDto.UserSearch search, Pageable pageable);
}
