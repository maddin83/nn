package de.neuronal.stock;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.neuronal.stock.DB.NNDBUtil;
import de.neuronal.stock.entity.NNStockValue;
import de.neuronal.stock.nets.NNNetUtil;
import de.neuronal.stock.stocks.NNStockWorker;

@Service
public class NNBasicService {
	
    private NNNetUtil netBuilder = new NNNetUtil();
    private NNStockWorker stockWorker = new NNStockWorker();
    
    @Autowired
    private NNDBUtil dbUtil;
    
    @Autowired
    EntityManager em;
    
    public void initiate() {

		// set up the neuronal network
		netBuilder.init();
	
		// get the stock values
		List<NNStockValue> stockValues = stockWorker.getHistoricalStockValues();
	
		// insert the stock values in DB and normalize them
		dbUtil.storeStockValues(stockValues);
	
		// train neuronal net with stock data

    }

	public String getStockCount() {
		return dbUtil.getStockCount();
	}

	public void getOneEntry() {
		 dbUtil.getOneEntry(); 
	}

}
