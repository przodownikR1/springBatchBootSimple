package pl.java.scalatech.skip;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.SkipListener;
@Slf4j
public class SkipSampleListener implements SkipListener<String, String>{

    @Override
    public void onSkipInRead(Throwable t) {
       log.info("SSS  onSkipInRead {}",t.getMessage());
        
    }

    @Override
    public void onSkipInWrite(String item, Throwable t) {
        log.info("SSS  onSkipInWrite {} {}",item,t.getMessage());
        
    }

    @Override
    public void onSkipInProcess(String item, Throwable t) {
        log.info("SSS  onSkipInProcess {} {}",item,t.getMessage());
        
    }

}
