package com.dentalflow.pe.soap.config;

import java.util.Arrays;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class SoapCorsConfig {
	 @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        
	        // 1. Permitir credenciales si tu frontend envía cookies o tokens
	        config.setAllowCredentials(true);
	        
	        // 2. IMPORTANTE: Si usas setAllowCredentials(true), NO puedes usar "*". 
	        // Debes usar 'allowedOriginPatterns' para permitir cualquier puerto local o remoto de manera segura.
	        config.setAllowedOriginPatterns(Arrays.asList("*")); 
	        
	        // 3. Cabeceras necesarias para SOAP (incluyendo SOAPAction)
	        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "SOAPAction"));
	        
	        // 4. Métodos HTTP obligatorios (SOAP usa POST, pero los navegadores envían OPTIONS primero)
	        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS"));
	        
	        // Aplicar esta regla a todas las rutas (por ejemplo /ws/* o /**)
	        source.registerCorsConfiguration("/**", config);
	        
	        return new CorsFilter(source);
	    }
}
