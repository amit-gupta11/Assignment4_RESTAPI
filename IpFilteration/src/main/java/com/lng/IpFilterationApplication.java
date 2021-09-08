package com.lng;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IpFilterationApplication implements CommandLineRunner  {

	@Value("${IP}")
	private String ips;
	public static List IP_LIST;
	public static void main(String[] args) {
		
		SpringApplication.run(IpFilterationApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		IP_LIST=Arrays.asList(ips.split(","));
	}

}
