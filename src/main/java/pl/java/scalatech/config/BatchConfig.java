package pl.java.scalatech.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    
    @Configuration
    @Profile("xml")
    @ImportResource("classpath:tasklet.xml")
    public static class BatchXmlConfig {
        
    }
    
}
