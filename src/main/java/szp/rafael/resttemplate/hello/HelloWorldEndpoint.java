package szp.rafael.resttemplate.hello;

import szp.rafael.resttemplate.RestInterface;
import szp.rafael.resttemplate.exception.BusinessException;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/hello")
@Produces(value = MediaType.APPLICATION_JSON)
public class HelloWorldEndpoint implements RestInterface {

  @GET
  @Produces("text/plain")
  public Response doGet() {
    return Response.ok("Hello from Thorntail!").build();
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(HelloDTO hello) {
    return Response.created(null).entity(hello).build();
  }

  @POST
  @Path("/internalError")
  public Response internalError(HelloDTO helloDTO) {
    throw new InternalServerErrorException("Wrong behavior when reading system resources");
  }

  @POST
  @Path("/businessError")
  public Response businessError(HelloDTO helloDTO) {
    debug(helloDTO);
    throw new BusinessException("Business Error");
  }


  @PUT
  public Response putCerto(HelloDTO dto) {
    System.out.println(" Esse funfa");
    return Response.ok(dto).build();
  }

  @PUT
  @Path("/{contid}/sistema/{idsistema}")
//  public Response putErrado(HelloDTO dto, @PathParam("contid") Integer contId, @PathParam("idsistema") Integer idSistema) {
  public Response putErrado(HelloDTO dto, @Context HttpServletRequest request) {
    System.out.println(" Nem deveria funcionar"+request);
    return Response.ok(dto).build();
  }

  @Override
  public Logger getLogger() {
    return Logger.getLogger(HelloWorldEndpoint.class.getName());
  }

  @Override
  public Response getList(UriInfo uriInfo) {
    return null;
  }

  @Override
  public Response getOneOrMany(UriInfo uriInfo,Long id) {
    return null;
  }
}
