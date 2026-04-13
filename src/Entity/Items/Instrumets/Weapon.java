package Entity.Items.Instrumets;

import Enum.Purpose;

public abstract class Weapon extends Instrument {
    public Weapon(int damage, float accuracy, String name, Purpose need) {
        super(damage, accuracy, Purpose.DAMAGE, name, need);
    }

    @Override
    public String toString() {
        return "Weapon{" + super.toString() + "}";
    }
}
