package com.tusharparmar.stock.stockservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tusharparmar.stock.stockservice.config.URLProperties;
import com.tusharparmar.stock.stockservice.util.AlphaVantageAPI;
import org.patriques.output.quote.data.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@EnableHystrixDashboard
@EnableHystrix
@RestController
@RequestMapping("/rest/stock")
public class StockResource
{
	@Autowired
	RestTemplate        restTemplate;
	URLProperties       urlProperties;

	public StockResource(URLProperties urlProperties)
	{
		this.urlProperties = urlProperties;
	}

	@HystrixCommand(groupKey = "stock", commandKey = "getStock", fallbackMethod = "getStockFallBack")
	@GetMapping("/{username}")
	public List<StockQuote> getStock(@PathVariable("username") final String username)
	{
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + username, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() { });


		List<String> quotes = quoteResponse.getBody();
		return AlphaVantageAPI.getStocks(quotes);
	}

	public List<StockQuote> getStockFallBack(@PathVariable("username") final String username)
	{
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + username, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() { });


		List<String> quotes = quoteResponse.getBody();
		return AlphaVantageAPI.getStocksFallBack(quotes);
	}

}
