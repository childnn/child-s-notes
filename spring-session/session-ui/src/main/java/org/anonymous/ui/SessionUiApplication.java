package org.anonymous.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/2/27 18:34
 */
@EnableRedisHttpSession
@SpringBootApplication
@EnableWebSecurity
public class SessionUiApplication {

    // disable base64 encoding of the sessionId in the cookie for demonstration
	/*@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setUseBase64Encoding(false);
		resolver.setCookieSerializer(cookieSerializer);
	    return resolver;
	}*/

    public static void main(String[] args) {
        SpringApplication.run(SessionUiApplication.class, args);
    }
}
