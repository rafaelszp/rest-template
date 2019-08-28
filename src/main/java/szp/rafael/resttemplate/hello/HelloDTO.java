package szp.rafael.resttemplate.hello;


import java.io.Serializable;

public class HelloDTO implements Serializable {

  private String fullName;
  private Integer age;
  private Integer childrenCount;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getChildrenCount() {
    return childrenCount;
  }

  public void setChildrenCount(Integer childrenCount) {
    this.childrenCount = childrenCount;
  }



}
