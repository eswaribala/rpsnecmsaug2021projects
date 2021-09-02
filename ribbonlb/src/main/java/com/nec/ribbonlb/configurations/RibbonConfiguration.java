package com.nec.ribbonlb.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
//Refer main for configuration --->@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class RibbonConfiguration {
	 @Autowired
	    IClientConfig ribbonClientConfig;
	 
	    @Bean
	    public IPing ribbonPing(IClientConfig config) {
	        return new PingUrl();
	    }
	 
	    @Bean
	    public IRule ribbonRule(IClientConfig config) {
	    	System.out.println("Entering....");
	        return new WeightedResponseTimeRule();
	    }
}

