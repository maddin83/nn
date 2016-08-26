package de.neuronal.stock.DB;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.neuronal.stock.entity.NNStockValue;

@Component
public interface NNRepository extends CrudRepository<NNStockValue, Long> {

	List<NNStockValue> findByName(String name);
	
	@Query(value="select distinct name from NNStockValue")
	List<String> findDistinctStockNamesy();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE NNStockValue SET close_norm = (close - (select min(close) from NNStockValue where name = ?1)) / ( (select max(close) from NNStockValue where name = ?1)*1.1 - (select min(close) from NNStockValue where name = ?1) ) "
			+ ", high_norm = (high - (select min(high) from NNStockValue where name = ?1)) / ( (select max(high) from NNStockValue where name = ?1)*1.1 - (select min(high) from NNStockValue where name = ?1) )"
			+ ", low_norm = (low - (select min(low) from NNStockValue where name = ?1)) / ( (select max(low) from NNStockValue where name = ?1)*1.1 - (select min(low) from NNStockValue where name = ?1) )"
			+ ", open_norm = (open - (select min(open) from NNStockValue where name = ?1)) / ( (select max(open) from NNStockValue where name = ?1)*1.1 - (select min(open) from NNStockValue where name = ?1) )"
			+ ", volume_norm = (volume - (select min(volume) from NNStockValue where name = ?1)) / ( (select max(volume) from NNStockValue where name = ?1)*1.1 - (select min(volume) from NNStockValue where name = ?1) )"
			+ ", time_norm = (timeInMillis - (select min(timeInMillis) from NNStockValue where name = ?1)) / ( (select max(timeInMillis) from NNStockValue where name = ?1)*1.002 - (select min(timeInMillis) from NNStockValue where name = ?1) )"
			+ "WHERE name = ?1")
	void normalizeStockValues(String stockName);
	
	
}
