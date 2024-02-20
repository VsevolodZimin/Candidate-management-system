package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class LabelEntity extends Entity {
    private String name;
    private int isPicked;

    public LabelEntity(Integer id, String name) {
        setId(id);
        setName(name);
    }
    
    public LabelEntity(String name) {
        setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelEntity that = (LabelEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}



