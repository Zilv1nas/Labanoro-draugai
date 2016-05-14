package lt.virai.labanoroDraugai.ui.context;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.format.DateTimeFormatter;

/**
 * Created by Žilvinas on 2016-05-14.
 */
public class CustomLocalDateDeserializer extends LocalDateDeserializer {
    public CustomLocalDateDeserializer(DateTimeFormatter dtf) {
        super(dtf);
    }
}
