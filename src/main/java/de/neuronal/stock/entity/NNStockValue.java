package de.neuronal.stock.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NNStockValue {

	public NNStockValue(String name, BigDecimal open, BigDecimal close, BigDecimal high,
			BigDecimal low, long volume, long timeInMillis) {
		super();
		this.name = name;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.timeInMillis = timeInMillis;
	}

	public NNStockValue(String name, BigDecimal open, BigDecimal close, BigDecimal high,
			BigDecimal low, long volume, BigDecimal open_norm, BigDecimal close_norm,
			BigDecimal high_norm, BigDecimal low_norm, BigDecimal volume_normm, long volume_norm,
			long timeInMillis) {
		super();
		this.name = name;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.open_norm = open_norm;
		this.close_norm = close_norm;
		this.high_norm = high_norm;
		this.low_norm = low_norm;
		this.volume_norm = volume_norm;
		this.timeInMillis = timeInMillis;
	}

	protected NNStockValue() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private BigDecimal open;
	private BigDecimal close;
	private BigDecimal high;
	private BigDecimal low;
	private long volume;
	private BigDecimal open_norm;
	private BigDecimal close_norm;
	private BigDecimal high_norm;
	private BigDecimal low_norm;
	private long volume_norm;
	private long timeInMillis;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public BigDecimal getOpen_norm() {
		return open_norm;
	}

	public void setOpen_norm(BigDecimal open_norm) {
		this.open_norm = open_norm;
	}

	public BigDecimal getClose_norm() {
		return close_norm;
	}

	public void setClose_norm(BigDecimal close_norm) {
		this.close_norm = close_norm;
	}

	public BigDecimal getHigh_norm() {
		return high_norm;
	}

	public void setHigh_norm(BigDecimal high_norm) {
		this.high_norm = high_norm;
	}

	public BigDecimal getLow_norm() {
		return low_norm;
	}

	public void setLow_norm(BigDecimal low_norm) {
		this.low_norm = low_norm;
	}

	public long getVolume_norm() {
		return volume_norm;
	}

	public void setVolume_norm(long volume_norm) {
		this.volume_norm = volume_norm;
	}

	public long getTimeInMillis() {
		return timeInMillis;
	}

	public void setTimeInMillis(long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}

	@Override
	public String toString() {
		return "NNStockValue [id=" + id + ", name=" + name + ", open=" + open + ", close=" + close
				+ ", high=" + high + ", low=" + low + ", volume=" + volume + ", open_norm="
				+ open_norm + ", close_norm=" + close_norm + ", high_norm=" + high_norm
				+ ", low_norm=" + low_norm + ", volume_norm=" + volume_norm + ", timeInMillis="
				+ timeInMillis + "]";
	}

}
