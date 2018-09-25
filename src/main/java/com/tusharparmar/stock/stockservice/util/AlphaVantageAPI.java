package com.tusharparmar.stock.stockservice.util;

import com.google.common.base.Joiner;
import org.patriques.AlphaVantageConnector;
import org.patriques.BatchStockQuotes;
import org.patriques.output.quote.data.StockQuote;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AlphaVantageAPI
{
	private static AlphaVantageAPI myObj;
	private static AlphaVantageConnector apiConnector       = new AlphaVantageConnector("DKM2E8CSJ9WV4KD4", 3000);
	private static BatchStockQuotes      batchStockQuotes   = new BatchStockQuotes(apiConnector);

	public static List<StockQuote> getStocks(List<String> listSymbols)
	{
		String symbols = Joiner.on(",").join(listSymbols);
		List<StockQuote> out = new ArrayList<>();
		try
		{
			out = batchStockQuotes.quote(symbols).getStockQuotes();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return out;
	}
}
