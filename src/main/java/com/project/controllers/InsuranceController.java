package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.SettlementRequestDTO;
import com.project.dto.SettlementResponseDTO;
import com.project.models.entity.Insured;
import com.project.services.interfaces.InsuranceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("v1.0/insurance")
public class InsuranceController {

	@Autowired
	private InsuranceService service;
	
	@Operation(summary = "Obtener la liquidación de un asegurado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liquidación retornada con exito", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = Insured.class)) })
	})
	@GetMapping("/settlement")
	public ResponseEntity<SettlementResponseDTO> getSettlement(@RequestBody SettlementRequestDTO request) {
		SettlementResponseDTO response = service.getSettlement(request);
		return ResponseEntity.ok().body(response);
	}
}
