package com.cts.tib.restTemplateConfig;
 
import java.util.Arrays;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
 
public class RestTemplateConfig {
	
	private static final RestTemplate TEMPLATE = new RestTemplate();


static{
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.featuresToEnable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);

    builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    builder.featuresToDisable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

    MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    jsonMessageConverter.setObjectMapper(builder.build());

    TEMPLATE.setMessageConverters(Arrays.asList(jsonMessageConverter));
    TEMPLATE.getMessageConverters().add(new FormHttpMessageConverter());
    TEMPLATE.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    TEMPLATE.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    
}


public static RestTemplate getTemplate() {
	return TEMPLATE;
}}