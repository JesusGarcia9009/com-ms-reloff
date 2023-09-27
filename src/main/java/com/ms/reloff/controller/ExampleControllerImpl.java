package com.ms.reloff.controller;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.reloff.client.EncompassClient;
import com.ms.reloff.dto.TokenResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${reloff.base-uri}/test")
public class ExampleControllerImpl implements ExampleController {
	
	/**
	 * Global variables
	 */
	private final EncompassClient encompassClient;
	
	@Override
	@GetMapping("/token")
	public ResponseEntity<TokenResponseDto> getTokenData() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		TokenResponseDto response = encompassClient.getToken("nveitia@encompass:BE11226696", "Homesavers#6");
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(response);
	}


	
	
}
