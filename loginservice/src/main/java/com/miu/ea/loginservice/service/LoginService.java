package com.miu.ea.loginservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.miu.ea.loginservice.dto.LoginDto;

@Service
public class LoginService {
	private final String URL = "http://localhost:8087/oauth/token";

	public String getAuthToken(LoginDto loginDto) {
		    HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        headers.setBasicAuth("client", "secret");

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type", "password");
	        params.add("username", loginDto.getUserName());
	        params.add("password", loginDto.getPassword());
	        params.add("scope", "WRITE");

	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<OAuth2AccessToken> response = restTemplate.postForEntity(URL, request, OAuth2AccessToken.class);

	        return response.getBody().getValue();
	}

}
