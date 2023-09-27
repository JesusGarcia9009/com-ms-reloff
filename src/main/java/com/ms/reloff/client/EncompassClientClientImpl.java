package com.ms.reloff.client;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.reloff.dto.TokenResponseDto;
import com.ms.reloff.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EncompassClientClientImpl implements EncompassClient {

	/**
	 * RestTemplateMs restTemplateMs
	 */
	@Autowired
	public RestTemplate restTemplate;

	@Value("${servicio.token}")
	private String tokenUrl;
	
	@Value("${servicio.client_secret}")
	private String clientSecret;
	
	@Value("${servicio.client_id}")
	private String clientId;

	@Override
	public TokenResponseDto getToken(String username, String password) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		// Parámetros de la solicitud
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
	    requestBody.add("grant_type", "password");
	    requestBody.add("username", username);
	    requestBody.add("password", password);
	    requestBody.add("client_id", clientId);
	    requestBody.add("client_secret", clientSecret);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<TokenResponseDto> response = new RestTemplate().exchange(
	            tokenUrl,
	            HttpMethod.POST,
	            requestEntity,
	            TokenResponseDto.class
	    );
		
        System.out.println(Utils.printCurlEquivalent(tokenUrl, HttpMethod.POST, requestEntity));
        
		TokenResponseDto responseBody = null;
		if (response.getStatusCode().is2xxSuccessful()) {
			responseBody = response.getBody();
		} else {
			throw new RuntimeException("Error al obtener el token de acceso: " + response.getStatusCode());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return responseBody;
	}
	
	

}
