package Entity.Items.Clothes;

public class Cap extends Cloth {
    public Cap() {
        super((float)0.9, "Шерстяная шапка");
    }

    @Override
    public String toString() {
        return "Cap{" + super.toString() + "}";
    }
}
