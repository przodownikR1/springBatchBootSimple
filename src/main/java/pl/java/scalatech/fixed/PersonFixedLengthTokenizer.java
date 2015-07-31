package pl.java.scalatech.fixed;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class PersonFixedLengthTokenizer extends FixedLengthTokenizer {
    public PersonFixedLengthTokenizer() {
        RangeArrayPropertyEditor range = new RangeArrayPropertyEditor();
        range.setAsText("1-20,21-40,41-");
        setNames(new String[] { "login", "passwd", "age" });
        setColumns((Range[]) range.getValue());
    }
}