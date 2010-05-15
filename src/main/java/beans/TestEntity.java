package beans;

import javax.persistence.*;

@Entity
public class TestEntity {

    @GeneratedValue
    @Id
    private Long id;
    private String stringValue;
    private Integer intValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    
}
