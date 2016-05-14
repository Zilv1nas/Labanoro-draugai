package lt.virai.labanoroDraugai.ui.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Žilvinas on 2016-04-21.
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        mapper = new ObjectMapper();
        JSR310Module jsr310Module = new JSR310Module();
        jsr310Module.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(jsr310Module);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}