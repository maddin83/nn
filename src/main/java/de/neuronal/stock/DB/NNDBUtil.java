package de.neuronal.stock.DB;

import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import de.neuronal.stock.entity.NNStockValue;

@Service
public class NNDBUtil {

	private NNRepository repo;

	@Autowired
	public NNDBUtil(NNRepository repo) {
		this.repo = Preconditions.checkNotNull(repo, "Houston, we have no Repo");
	}

	/**
	 * insert Stock values into DB and normalize them
	 * 
	 * @param historicalStockValues
	 */
	public void storeStockValues(List<NNStockValue> stockValues) {
		repo.save(stockValues);
		normalizeAll();
	}

	private void normalizeAll() {
		List<String> stockNames = repo.findDistinctNameBy();
		for (String stockName : stockNames) {
			repo.normalizeStockValues(stockName);
		}
	}

	public String getStockCount() {
		return String.valueOf(repo.count());
	}

	public void getOneEntry() {
		Iterable<NNStockValue> findAll = repo.findAll();
		for (NNStockValue nnStockValue : findAll) {
			System.out.println(nnStockValue.toString());
		}
		
	}
	
}
