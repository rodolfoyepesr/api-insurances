package com.project.services.implementations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.SettlementDTO;
import com.project.dto.SettlementRequestDTO;
import com.project.dto.SettlementResponseDTO;
import com.project.models.entity.Insured;
import com.project.models.entity.Premium;
import com.project.models.repository.InsuredRepository;
import com.project.models.repository.PremiumRepository;
import com.project.services.interfaces.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	private InsuredRepository Insuredrepo;
	
	@Autowired
	private PremiumRepository premiumRepo;
	
	@Override
	public SettlementResponseDTO getSettlement(SettlementRequestDTO request) {
		SettlementResponseDTO response = new SettlementResponseDTO();
		List<SettlementDTO> settlements = new ArrayList<>();
		BigDecimal total = null;
		
		response.setTypeId(request.getTypeId());
		response.setNumberId(request.getNumberId());
		response.setValue(request.getValue());
		
		Iterable<Premium> premiums = premiumRepo.findAll();
		
		for (Premium premium : premiums) {
			if (validateAgeForPremium(getAge(request.getNumberId()), premium.getAgeMin(), premium.getAgeMax())) {
				SettlementDTO settlement = new SettlementDTO();
				settlement.setCode(premium.getProtection().getCode());
				settlement.setName(premium.getProtection().getName());
				settlement.setPremium(request.getValue() * premium.getPremium());
				settlements.add(settlement);
				
				total = new BigDecimal(settlement.getPremium());
			}
		}
				
		response.setSettlement(settlements);
		response.setTotal(total);
		
		return response;
	}
	
	private int getAge(Long numberId) {
		int age = 0;
		Optional<Insured> insuredOpt = Insuredrepo.findByNumberId(numberId);
		if (insuredOpt.isPresent()) {
			Insured insured = insuredOpt.get();
			Period period = Period.between(LocalDate.now(), insured.getBirthdate());
			age = period.getYears();
		}
		
		return age;
	}
	
	private boolean validateAgeForPremium(int age, int ageMin, int ageMax) {
		if (age >= ageMin && age <= ageMax) {
			return true;
		}
		
		return false;
	}
	
}
