package com.project.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SettlementResponseDTO {
	private Long typeId;
	private Long numberId;
	private Long value;
	private List<SettlementDTO> settlement;
	private BigDecimal total;
}
