package com.tusharparmar.stock.stockservice.util;

import org.patriques.AlphaVantageConnector;
import org.patriques.BatchStockQuotes;
import org.patriques.output.quote.data.StockQuote;

import java.util.List;
import java.util.StringJoiner;

public class AlphaVantageAPI
{
	private static AlphaVantageAPI myObj;
	private static AlphaVantageConnector apiConnector       = new AlphaVantageConnector("DKM2E8CSJ9WV4KD4", 3000);
	private static BatchStockQuotes      batchStockQuotes   = new BatchStockQuotes(apiConnector);

	public static StockQuote getStock(String symbol)
	{
		try
		{
			return batchStockQuotes.quote(symbol).getStockQuotes().get(0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new StockQuote(symbol,0,0,null);
		}
	}

	/*public static StockQuote getStocks(List<String> symbols)
	{
		try
		{
			return batchStockQuotes.quote(symbol).getStockQuotes().get(0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new StockQuote(symbol,0,0,null);
		}
	}*/
}
