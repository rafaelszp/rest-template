package szp.rafael.resttemplate.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class BadRequestMapper implements ExceptionMapper<InvalidFormatException> {

  Logger logger = Logger.getLogger(BadRequestMapper.class.getName());

  @Context
  HttpServletRequest request;

  @Override
  public Response toResponse(InvalidFormatException exception) {


    String message = String.format("ERRO STATUS: [%s]. Campo [%s] | Valor inserido: [%s] | Request: [%s %s] ", Response.Status.BAD_REQUEST.getStatusCode(),
            exception.getPath().get(0).getFieldName(), exception.getValue(),request.getMethod(),request.getRequestURI());
    logger.log(Level.FINE,message);

    List<Error> errors = new ArrayList<>();
    errors.add(new Error(message, Response.Status.BAD_REQUEST.getStatusCode()));

    return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
  }


}
