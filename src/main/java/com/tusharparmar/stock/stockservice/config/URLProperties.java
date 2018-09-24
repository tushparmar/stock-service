package com.tusharparmar.stock.stockservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;

@Configuration
@PropertySource("classpath:URLProperties.properties")
public class URLProperties
{
	@NotNull

	private String dbServiceQuotesURL;

	public String getDbServiceQuotesURL()
	{
		return dbServiceQuotesURL;
	}
}
