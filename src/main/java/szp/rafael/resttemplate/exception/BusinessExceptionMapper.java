package szp.rafael.resttemplate.exception;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

  Logger logger = Logger.getLogger(BusinessExceptionMapper.class.getName());


  @Context
  HttpServletRequest request;

  @Override
  public Response toResponse(BusinessException exception) {

    Map<String, String[]> parameterMap = request.getParameterMap();
    String message = String.format("ERRO STATUS: [%s]. | Cause: [%s] | Request: [%s %s] ", BusinessException.getStatusCode(),
             exception.getMessage(), request.getMethod(), request.getRequestURI());
    logger.log(Level.INFO, message);

    List<Error> errors = new ArrayList<>();
    errors.add(new Error(message, BusinessException.getStatusCode()));

    return Response.status(BusinessException.getStatusCode()).entity(errors).build();
  }


}
