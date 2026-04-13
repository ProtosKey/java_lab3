package Entity.Items.Clothes;

public class Shirt extends Cloth {
    public Shirt() {
        super((float)0.9, "Рубаха");
    }

    @Override
    public String toString() {
        return "Shirt{" + super.toString() + "}";
    }
}
