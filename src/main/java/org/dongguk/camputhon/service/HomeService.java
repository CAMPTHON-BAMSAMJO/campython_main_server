package org.dongguk.camputhon.service;

import org.dongguk.camputhon.dto.HomeResponseDTO;

public interface HomeService {
    public HomeResponseDTO.Home getHomData(Long userId);
}
