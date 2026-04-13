package Entity.Items.Instrumets;

public class Shotgun extends Rifle {
    public Shotgun() {
        super(-100, (float) 0.7, 5, "Дробовик");
    }

    @Override
    public String toString() {
        return "Shotgun{" + super.toString() + "}";
    }
}
