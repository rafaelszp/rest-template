package szp.rafael.resttemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import szp.rafael.resttemplate.car.Car;
import szp.rafael.resttemplate.car.CarEndpoint;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("all")
public interface RestInterface<T> {

  default void debug(Object dto) {
    try {
      String json = new ObjectMapper().writeValueAsString(dto);
      getLogger().info(json);
    } catch (JsonProcessingException e) {
      getLogger().fine("Impossible to log");
    }
  }

  default Map getMapParameter(UriInfo uriInfo, String key) {
    ObjectMapper jsonMapper = new ObjectMapper();
    jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    jsonMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    jsonMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
    try {
      if (queryParameters.get(key) == null) {
        return null;
      }
      String parameterString = queryParameters.get(key).get(0);
      Map filter = jsonMapper.readValue(parameterString, Map.class);
      return filter;
    } catch (IOException e) {
      throw new BadRequestException("Invalid " + key);
    }
  }

  default List<String> getListParameter(UriInfo uriInfo, String key) {
    ObjectMapper jsonMapper = new ObjectMapper();
    jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    jsonMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    jsonMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
    try {
      if (queryParameters.get(key) == null) {
        return null;
      }
      String parameterString = queryParameters.get(key).get(0);
      List<String> sort = jsonMapper.readValue(parameterString, List.class);
      return sort;
    } catch (IOException e) {
      throw new BadRequestException("Invalid " + key);
    }
  }

  default Response createdResponse(Number id) {
    return Response.created(
            UriBuilder.fromResource(this.getClass())
                    .path(String.valueOf(id)).build()).build();
  }
  default Response updatedResponse(Number id) {
    return Response.ok().header("Location",
            UriBuilder.fromResource(this.getClass())
                    .path(String.valueOf(id)).build()).build();
  }

  Logger getLogger();

  Response getList(UriInfo uriInfo);
  Response getOneOrMany(UriInfo uriInfo, Long id);
  Response create(T dto);
  Response update(Long id);


}
