package szp.rafael.resttemplate.exception;

import javax.ws.rs.WebApplicationException;

public class BusinessException extends WebApplicationException {

  public static int getStatusCode(){
    return 422;
  }

  public BusinessException(String message) {
    super(message,BusinessException.getStatusCode());
  }

  public BusinessException(String message,Throwable e){
    super(message,e,BusinessException.getStatusCode());
  }

}
