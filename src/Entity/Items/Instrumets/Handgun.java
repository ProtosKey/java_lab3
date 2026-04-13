package Entity.Items.Instrumets;

public class Handgun extends Rifle {
    public Handgun() {
        super(-80, (float) 0.8, 15, "Пистолет");
    }

    @Override
    public String toString() {
        return "Handgun{" + super.toString() + "}";
    }
}
