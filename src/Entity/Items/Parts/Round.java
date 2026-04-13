package Entity.Items.Parts;

import Entity.Items.Item;
import Enum.Purpose;

public class Round extends Item {
    public Round() {
        super(Purpose.BULLET, "Патрон");
    }

    @Override
    public String toString() {
        return "Round{" + super.toString() + "}";
    }
}
