package pl.java.scalatech;

import org.springframework.batch.admin.annotation.EnableBatchAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

@SpringBootApplication(exclude = { HypermediaAutoConfiguration.class, MultipartAutoConfiguration.class })
@EnableBatchAdmin
public class BatchBootSimple {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BatchBootSimple.class, args);
    }
}
