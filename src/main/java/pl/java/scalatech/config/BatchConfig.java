package pl.java.scalatech.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@EnableBatchProcessing
@Configuration
@ImportResource("classpath:tasklet.xml")
@Profile(value = { "java", "simpleString" })
public class BatchConfig {

}
