
package de.neuronal.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.neuronal.stock.NNBasicService;

@RestController
public class NnRestController {

    private NNBasicService basicService;

    @Autowired
    public void setBasicService(NNBasicService basicService) {
    	this.basicService = basicService;
    }

    @RequestMapping("/init")
    public String init() {
    	basicService.initiate();
    	System.out.println("re-called basicService.initiate()");
    	return "done";
    }
    
    @RequestMapping("/fetchStocks")
    public String fetchStocks(@RequestParam(defaultValue="1", name="years", required=false) int years){
    	basicService.fetchAndStoreStockValues(years);
    	return ("done");
    }
    
    @RequestMapping("/getStockCount")
    public String getDbCount(){
    	return basicService.getStockCount();
    }
    
    @RequestMapping("/getOneEntry")
    public void getOneEntry(){
    	 basicService.getOneEntry();
    }
    
    // @RequestMapping("/test")
    // public double[] test(@RequestParam(value = "in1", defaultValue = "1") int
    // in1,
    // @RequestParam(value = "in2", defaultValue = "1") int in2) {
    // // myMlPerceptron.setInput();
    // myMlPerceptron.calculate();
    // return myMlPerceptron.getOutput();
    // }
}
