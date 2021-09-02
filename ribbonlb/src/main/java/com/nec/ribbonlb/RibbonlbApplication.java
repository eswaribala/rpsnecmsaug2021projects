package com.nec.ribbonlb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.nec.ribbonlb.configurations.RibbonConfiguration;

@SpringBootApplication
@RibbonClient(name = "NEC-RIBBON", configuration = RibbonConfiguration.class)

public class RibbonlbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonlbApplication.class, args);
	}

}
