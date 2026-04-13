package Entity.Items.Clothes;

public class Trousers extends Cloth {
    public Trousers() {
        super((float)0.9, "Шаровары");
    }

    @Override
    public String toString() {
        return "Trousers{" + super.toString() + "}";
    }
}
