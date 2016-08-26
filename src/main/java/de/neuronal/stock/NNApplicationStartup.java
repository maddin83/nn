package de.neuronal.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NNApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private NNBasicService basicService;

	@Autowired
	public void setBasicService(NNBasicService basicService) {
		this.basicService = basicService;
	}

	/**
	 * This event is executed as late as conceivably possible to indicate that
	 * the application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		basicService.initiate();
		System.out.println("NNApplicationStartup - called basicService.initiate()");
	}
}