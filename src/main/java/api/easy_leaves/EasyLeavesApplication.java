package api.easy_leaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EasyLeavesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyLeavesApplication.class, args);
	}

}
