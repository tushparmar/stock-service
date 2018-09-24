package com.tusharparmar.stock.stockservice.resource;

import com.tusharparmar.stock.stockservice.config.URLProperties;
import com.tusharparmar.stock.stockservice.util.AlphaVantageAPI;
import org.patriques.output.quote.data.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/{username}")
	public List<StockQuote> getStock(@PathVariable("username") final String username)
	{
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/" + username, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() { });


		List<String> quotes = quoteResponse.getBody();
		return quotes.stream().map(AlphaVantageAPI::getStock).collect(Collectors.toList());
	}

}
