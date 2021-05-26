package com.homework.packagedelivery;

import com.homework.packagedelivery.services.DeliveryProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

@RequiredArgsConstructor
@SpringBootApplication
public class PackageDeliveryApplication implements CommandLineRunner, ApplicationContextAware {

	public static void main(String[] args) {
		SpringApplication.run(PackageDeliveryApplication.class, args);
	}

	private final DeliveryProcessService deliveryProcessService;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		this.applicationContext = ctx;
	}

	@Override
	public void run (String... args) {
		deliveryProcessService.loadPackagesProcess();
		((ConfigurableApplicationContext) applicationContext).close();
	}
}
