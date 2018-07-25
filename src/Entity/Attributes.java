package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;


public class  Attributes {
    int id;
    String name;
    int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Attributes(){}

    public Attributes(String name) {
        this.name=name;
    }

}
