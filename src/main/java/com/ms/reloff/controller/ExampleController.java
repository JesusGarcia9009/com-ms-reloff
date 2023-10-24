package com.ms.reloff.controller;

import org.springframework.http.ResponseEntity;

import com.ms.reloff.dto.DashboardDataDto;
import com.ms.reloff.dto.TokenResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ExampleController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ExampleController")
public interface ExampleController {

	/**
	 * Method to test EncompassClient 
	 * 
	 * @param none
	 * @return dto @See {@link DashboardDataDto}
	 */
	@ApiOperation(value = "Get token data", notes = "Retorna los datos token")
	public ResponseEntity<TokenResponseDto> getTokenData();

	@ApiOperation(value = "Get loan data", notes = "Retorna los datos del loan")
	public <T> ResponseEntity<T> getLoanData(String eToken, String loanId);
	
	
	
	
}
