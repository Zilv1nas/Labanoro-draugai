package lt.virai.labanoroDraugai.ui.context;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Žilvinas on 2016-05-14.
 */
public class CustomLocalDateDeserializer extends LocalDateDeserializer {
    private static final long serialVersionUID = -1207700945409814434L;

    private final LocalDateDeserializer lds = LocalDateDeserializer.INSTANCE;

    public CustomLocalDateDeserializer() {
        super(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        try {
            return super.deserialize(parser, context);
        } catch (DateTimeException e) {
            return lds.deserialize(parser, context);
        }
    }
}
