package org.dongguk.camputhon.service;

import org.dongguk.camputhon.dto.ShortListResponseDTO;

public interface ShortListService {
    public ShortListResponseDTO.ShortList getShortList(Long userId);
}
