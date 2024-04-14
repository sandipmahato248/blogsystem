package com.miu.ea.security.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miu.ea.security.dto.UserDto;

@FeignClient("USER-SERVICE")
public interface UserFeignClient {
	
	 @GetMapping("/users/get/{userName}")
     UserDto getUser(@PathVariable String userName);

}
