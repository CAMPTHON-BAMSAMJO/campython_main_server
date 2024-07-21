package org.dongguk.camputhon.dto;

import lombok.Getter;
import org.dongguk.camputhon.domain.enums.Gender;

@Getter
public class UserUUIDRequestDTO {
    private String uuid;
    private Gender sex;
}
