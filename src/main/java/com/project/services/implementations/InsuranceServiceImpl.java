package com.project.services.implementations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.SettlementDTO;
import com.project.dto.SettlementRequestDTO;
import com.project.dto.SettlementResponseDTO;
import com.project.models.entity.Insured;
import com.project.models.entity.Premium;
import com.project.models.entity.Protection;
import com.project.models.repository.InsuredRepository;
import com.project.models.repository.PremiumRepository;
import com.project.models.repository.ProtectionRepository;
import com.project.services.interfaces.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	private InsuredRepository Insuredrepo;
	
	@Autowired
	private PremiumRepository premiumRepo;
	
	@Autowired
	private ProtectionRepository protectionRepo;

	@Override
	public SettlementResponseDTO getSettlement(SettlementRequestDTO request) {
		SettlementResponseDTO response = new SettlementResponseDTO();
		response.setTypeId(request.getTypeId());
		response.setNumberId(request.getNumberId());
		response.setValue(request.getValue());
		
		Map<Long, Double> premiumsOfInsured = new HashMap<>();
		BigDecimal total = null;
		
		Iterable<Premium> premiums = premiumRepo.findAll();
		
		for (Premium premium : premiums) {
			if (validateAge(getAge(request.getNumberId()), premium.getAgeMin(), premium.getAgeMax())) {
				premiumsOfInsured.put(premium.getCode(), premium.getPremium());
			}
		}
		
		List<SettlementDTO> settlements = new ArrayList<>();
		SettlementDTO settlement = new SettlementDTO();
		
		for (Map.Entry<Long, Double> entry : premiumsOfInsured.entrySet()) {
			settlement.setCode(entry.getKey());
			settlement.setPremium(request.getValue() * entry.getValue());
			total = new BigDecimal(settlement.getPremium());
			
			Optional<Protection> protectionOpt = protectionRepo.findByCode(entry.getKey());
			if (protectionOpt.isPresent()) {
				Protection protection = protectionOpt.get();
				settlement.setName(protection.getName());
			}
			
			settlements.add(settlement);
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
	
	private boolean validateAge(int age, int ageMin, int ageMax) {
		if (age >= ageMin && age <= ageMax) {
			return true;
		}
		
		return false;
	}
	
}
