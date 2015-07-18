package pl.java.scalatech.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@EnableBatchProcessing
@Configuration
@ImportResource("classpath:tasklet.xml")
public class BatchConfig {

}
