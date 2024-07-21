package org.dongguk.camputhon.service;

import org.dongguk.camputhon.dto.ShortListResponseDTO;
import org.dongguk.camputhon.dto.ShortRequestDTO;

public interface ShortListService {
    public void createShort(ShortRequestDTO shortRequestDTO);
    public ShortListResponseDTO.ShortList getShortList(Long userId);
}
