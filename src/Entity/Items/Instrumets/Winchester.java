package Entity.Items.Instrumets;

public class Winchester extends Rifle {
    public Winchester() {
        super(-150, (float) 0.9, 8, "Винчестер");
    }

    @Override
    public String toString() {
        return "Winchester{" + super.toString() + "}";
    }
}
