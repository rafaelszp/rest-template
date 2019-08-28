package szp.rafael.resttemplate.exception;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ServerErrorMapper implements ExceptionMapper<InternalServerErrorException> {
  @Override
  public Response toResponse(InternalServerErrorException exception) {

    List<Error> errors = new ArrayList<>();
    errors.add(new Error(exception.getMessage(),Response.Status.fromStatusCode(exception.getResponse().getStatus()).getStatusCode()));

    return Response.serverError().entity(errors).build();
  }
}
