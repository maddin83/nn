package de.neuronal.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.neuronal.stock.DB.NNDBUtil;
import de.neuronal.stock.entity.NNStockValue;
import de.neuronal.stock.nets.NNNetUtil;
import de.neuronal.stock.stocks.NNStockWorker;

@Service
public class NNBasicService {
	
    private final NNNetUtil netBuilder = new NNNetUtil();
    private final NNStockWorker stockWorker = new NNStockWorker();
    private NNDBUtil dbUtil;

    @Autowired
    public void setDbUtil(NNDBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public void initiate() {
		// set up the neuronal network
		netBuilder.init();
    }
    
    public void fetchAndStoreStockValues(int years){
    	
    	//TODO find out if there are already values fetched and which is the last date
    	
    	// 	get the stock values
 		List<NNStockValue> stockValues = stockWorker.getHistoricalStockValues(years);
 	
 		// insert the stock values in DB and normalize them
 		dbUtil.storeStockValues(stockValues);
 	
 		// train neuronal net with stock data
 		//TODO
    }
    
	public String getStockCount() {
		return dbUtil.getStockCount();
	}

	public void getOneEntry() {
		 dbUtil.getOneEntry();
	}


}
