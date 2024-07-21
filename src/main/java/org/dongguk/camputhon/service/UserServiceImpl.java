package org.dongguk.camputhon.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.Repository.UserRepository;
import org.dongguk.camputhon.domain.User;
import org.dongguk.camputhon.domain.enums.ShortType;
import org.dongguk.camputhon.dto.UserUUIDRequestDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // 유저 생성 서비스
    public Long createUser(UserUUIDRequestDTO request) {
        User user = User.builder()
                .uuid(request.getUuid())
                .shortType(ShortType.NONE)
                .sex(request.getSex())
                .build();

        // db에 저장
        User newUser = userRepository.save(user);

        return newUser.getId();
    }
}
