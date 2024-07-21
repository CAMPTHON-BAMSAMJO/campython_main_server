package org.dongguk.camputhon.service;

import org.dongguk.camputhon.dto.UserUUIDRequestDTO;

public interface UserService {
    public Long createUser(UserUUIDRequestDTO request);
}
