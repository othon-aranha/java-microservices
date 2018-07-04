package cloud.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthServerJwtConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;
	
    //@Autowired TokenStore tokenStore;
    
    @Autowired JwtAccessTokenConverter accessTokenConverter;
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
	
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                 .accessTokenConverter(accessTokenConverter)
                 .authenticationManager(authenticationManager);
    }	
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	    KeyStoreKeyFactory keyStoreKeyFactory = 
	      new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"), "mypass".toCharArray());
	    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("security-server"));
	    return converter;
	}	
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
    	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new JwtTokenEnhancer(), 
				accessTokenConverter()));
        return tokenEnhancerChain;
    }
}
