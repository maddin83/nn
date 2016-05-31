package de.neuronal.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.neuronal.stock.DB.NNDBUtil;

@SpringBootApplication
public class NeuronalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuronalApplication.class, args);
		NNDBUtil.initH2DB();
		System.out.println(NNDBUtil.getDaxValues());
	}

	
}
