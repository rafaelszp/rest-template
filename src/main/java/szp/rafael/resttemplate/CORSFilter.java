package szp.rafael.resttemplate;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    String origin = responseContext.getHeaderString("Origin") != null ? responseContext.getHeaderString("Origin") : requestContext.getHeaderString("Origin");
    origin = origin == null ? "*" : origin;
    responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
    responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Access-Control-Allow-Headers, Authorization, X-Requested-With, Headers, withCredentials");
    responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    responseContext.getHeaders().add("Access-Control-Expose-Headers", "Content-Range");
    responseContext.getHeaders().add("Access-Control-Max-Age", Integer.MAX_VALUE+"");
  }
}