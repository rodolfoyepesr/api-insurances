package com.project.services.interfaces;

import com.project.dto.SettlementRequestDTO;
import com.project.dto.SettlementResponseDTO;

public interface InsuranceService {
	
	public SettlementResponseDTO getSettlement(SettlementRequestDTO request);
}
