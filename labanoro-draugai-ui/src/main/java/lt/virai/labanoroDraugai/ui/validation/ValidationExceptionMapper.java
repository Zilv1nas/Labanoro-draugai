package lt.virai.labanoroDraugai.ui.validation;

import lt.virai.labanoroDraugai.bl.exceptions.LabanoroException;

import javax.ejb.EJBException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Žilvinas on 2016-05-04.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<EJBException> {
    @Override
    public Response toResponse(EJBException exception) {
        Throwable cause = exception;
        while (cause.getCause() != null) {
            if (cause.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException ex = (ConstraintViolationException) cause.getCause();
                List<ValidationError> validationErrors = ex.getConstraintViolations().stream().map(this::mapToValidationError).collect(Collectors.toList());

                return Response.status(Response.Status.BAD_REQUEST).entity(validationErrors).build();
            } else if (cause.getCause() instanceof LabanoroException) {
                LabanoroException ex = (LabanoroException) cause.getCause();
                return Response.status(Response.Status.BAD_REQUEST).entity(new ValidationError(ex.getKey(), ex.getMessage())).build();
            }
            cause = cause.getCause();
        }
        throw exception;
    }

    private ValidationError mapToValidationError(ConstraintViolation<?> constraintViolation) {
        String message = constraintViolation.getMessage();
        String key = StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second)
                .map(Path.Node::getName)
                .orElse(null);

        return new ValidationError(key, message);
    }

    public static class ValidationError {
        private String key;
        private String message;

        public ValidationError(String key, String message) {
            this.key = key;
            this.message = message;
        }

        public ValidationError() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
