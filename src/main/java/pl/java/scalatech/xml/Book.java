package pl.java.scalatech.xml;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@XmlRootElement(name = "book")
public class Book {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Integer author;

}