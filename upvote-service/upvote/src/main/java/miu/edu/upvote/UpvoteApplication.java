package miu.edu.upvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UpvoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpvoteApplication.class, args);
	}

}
