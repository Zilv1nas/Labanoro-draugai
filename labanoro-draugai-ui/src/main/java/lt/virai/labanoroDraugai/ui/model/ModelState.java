package lt.virai.labanoroDraugai.ui.model;

import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Mantas on 4/19/2016.
 */
public class ModelState {
    private Map<String, List<String>> errors = new HashMap<String, List<String>>();

    public void addError(String key, String message) {
        if(errors.containsKey(key)){
            errors.get(key).add(message);
        }
        else {
            errors.put(key, Arrays.asList(message));
        }
    }

    public Map<String, List<String>> getErrors(){
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() != 0;
    }

    public Response buildBadResponse() {
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }

    public void merge(ModelState modelState, String key){
        if(modelState == null || !modelState.hasErrors())
            return;
        Objects.requireNonNull(key);
        Map<String, List<String>> otherErrors = modelState.getErrors();
        String[] errorKeys = (String[]) otherErrors.keySet().toArray();
        for (String errorKey : errorKeys){
            List<String> errorValues = otherErrors.get(errorKey);
            String newKey = key + '.' + errorKey;
            for(String error : errorValues){
                addError(newKey, error);
            }
        }
    }
}
