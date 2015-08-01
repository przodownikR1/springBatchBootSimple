package pl.java.scalatech.continue_;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
public class ChunkContinueListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("$$$ before chunk  complete -> {} , step name {}", context.isComplete(), context.getStepContext().getStepName());

    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.info("$$$ after chunk  complete -> {} , step name {}", context.isComplete(), context.getStepContext().getStepName());

    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.info("$$$ after chunk error  complete -> {} , step name {}", context.isComplete(), context.getStepContext().getStepName());

    }

}
