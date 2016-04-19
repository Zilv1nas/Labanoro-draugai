package lt.virai.labanoroDraugai.ui.model;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mantas on 4/19/2016.
 */
public class ModelState {
    private Map<String, String> errors = new HashMap<String, String>();

    public void addError(String key, String message){
        errors.put(key, message);
    }
    
    public boolean hasErrors(){
        return errors.size() != 0;
    }

    public Response buildBadResponse(){
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
