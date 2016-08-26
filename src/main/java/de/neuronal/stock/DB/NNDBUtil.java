package de.neuronal.stock.DB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import de.neuronal.stock.entity.NNStockValue;

@Service
public class NNDBUtil {

	private final NNRepository repo;

	@Autowired
	public NNDBUtil(NNRepository repo) {
		this.repo = Preconditions.checkNotNull(repo, "Houston, we have no Repo");
	}

	/**
	 * insert Stock values into DB and normalize them
	 * 
	 * @param stockValues
	 */
	public void storeStockValues(List<NNStockValue> stockValues) {
		repo.save(stockValues);
		normalizeAll();
	}

	private void normalizeAll() {
		List<String> stockNames = repo.findDistinctStockNamesy();
		stockNames.forEach(repo::normalizeStockValues);
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
