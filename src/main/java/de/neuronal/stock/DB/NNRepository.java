package de.neuronal.stock.DB;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import de.neuronal.stock.entity.NNStockValue;

public interface NNRepository extends CrudRepository<NNStockValue, Long> {

	List<NNStockValue> findByName(String name);

	List<String> findDistinctNameBy();
	
	@Transactional
	@Modifying
	@Query(name = "normalizeAll", nativeQuery=true, value = "UPDATE NNStockValue SET close_norm = (close - min(close))/(max(close)*1.1 - min(close)) WHERE name in (SELECT DISTINCT name FROM NNStockValue)")
	void normalizeAll();
	
	@Transactional
	@Modifying
	@Query(name = "normalizeStockValues", nativeQuery=true,  value = "UPDATE NNStockValue SET close_norm = (close - min(select close from nnstockvalue where name = ?1))/(max(select close from nnstockvalue where name = ?!)*1.1 - min(select close from nnstockvalue where name = ?!)) WHERE name = ?1)")
	void normalizeStockValues(String string);
	
	long count();
}
	
