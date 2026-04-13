package Entity.Items.Clothes;

import Entity.Items.Item;
import Enum.*;

import java.util.Objects;

public class Cloth extends Item {
    protected float kf;

    public Cloth(float kf, String name) {
        super(Purpose.CLOTH, name);
        this.kf = kf;
    }

    public float getKf() {
        return kf;
    }

    @Override
    public String toString() {
        return "Cloth{" + super.toString() +
                ",kf=" + kf +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cloth cloth = (Cloth) o;
        return Float.compare(cloth.kf, kf) == 0;
    }
}
