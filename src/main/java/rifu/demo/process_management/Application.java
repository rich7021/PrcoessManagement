package rifu.demo.process_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import rifu.demo.process_management.config.ThreadPoolTaskExecutorConfig;

@SpringBootApplication
@Import(ThreadPoolTaskExecutorConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
