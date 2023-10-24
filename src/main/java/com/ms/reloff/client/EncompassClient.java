package com.ms.reloff.client;

import com.ms.reloff.dto.Prequalification;
import com.ms.reloff.dto.TokenResponseDto;

/**
 * EncompassClient Service
 * 
 * @author Jesus Garcia - Open2000
 * @version 0.1
 * @since jdk-11.0.7
 */
public interface EncompassClient {

	
	/**
	 * Get token by username and password.- Spring Boot
	 *
	 * @param String username and password see {@link String}
	 * @return token see {@link TokenResponseDto}
	 */
	public TokenResponseDto getToken(String username, String password);

	/**
	 * Getting a full Loan data
	 */
	public Prequalification getLoan(String eToken, String loanId);
	
}
