package szp.rafael.resttemplate.car;

import szp.rafael.resttemplate.RestInterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Path("/cars")
@Produces(value = MediaType.APPLICATION_JSON)
public class CarEndpoint implements RestInterface {
  @Override
  public Logger getLogger() {
    return Logger.getLogger(this.getClass().getName());
  }

  @Override
  @GET
  public Response getList(@Context UriInfo uriInfo) {
    Map filter = getMapParameter(uriInfo,"filter");
    List<String> sortList = getListParameter(uriInfo,"sort");
    List<String> rangeList = getListParameter(uriInfo,"range");
    int start = 1;
    int end = 10;
    int total = 100;
    debug(filter);
    debug(sortList);
    debug(rangeList);
    return Response.ok()
            .header("Content-Range",String.format("%d-%d/%d",start,end,total))
            .build();
  }

  @GET
  @Path("/{id: \\d+}")
  @Override
  public Response getOneOrMany(@Context UriInfo uriInfo, @PathParam("id") Long id) {
    Map filter = getMapParameter(uriInfo,"filter");
    if(filter!=null){
      List<Car> carList = new ArrayList<>(1);
      int start = 1;
      int end = 10;
      int total = 100;
      debug(filter);
      debug(carList);
      return Response
              .status(Response.Status.OK)
              .header("Content-Range",String.format("%d-%d/%d",start,end,total))
              .entity(carList)
              .build();
    }
    Car singleCar = new Car();
    debug(singleCar);
    return Response.ok(singleCar).build();
  }
}
