package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity {
    
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    private int id;
    private int important = FALSE;
    private int forStatistics = FALSE;
}
