package szp.rafael.resttemplate.car;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable {

  private Long id;
  private String brand;
  private String modelName;
  private List<Door> doors;
  private List<Passenger> passengers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public List<Door> getDoors() {
    return doors;
  }

  public void setDoors(List<Door> doors) {
    this.doors = doors;
  }

  public List<Passenger> getPassengers() {
    return passengers;
  }

  public void setPassengers(List<Passenger> passengers) {
    this.passengers = passengers;
  }
}
