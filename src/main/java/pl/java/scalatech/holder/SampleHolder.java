package pl.java.scalatech.holder;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SampleHolder implements Serializable {
    private static final long serialVersionUID = -6033533934661711368L;
    @Setter
    @Getter
    private String message;
    
}
