package com.miu.ea.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.miu.ea.security.service.CustomUserDetailService;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

 	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		security.checkTokenAccess("permitAll()");
	}
   

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		  clients.inMemory().withClient("client")
		  .secret(bCryptPasswordEncoder.encode("secret"))
		  .scopes("READ","WRITE","DELETE")
		  .authorizedGrantTypes("password","authorization_code","refresh_token")
		  .accessTokenValiditySeconds(3600)
		  .refreshTokenValiditySeconds(18000);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
        .authenticationManager(authenticationManager)
        .userDetailsService(customUserDetailService);
	}

}
