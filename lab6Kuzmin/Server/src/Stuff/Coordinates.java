package Stuff;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Float x; //Максимальное значение поля: 30, Поле не может быть null
    private float y; //Максимальное значение поля: 387

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
