package Acts;

import Errors.LocationException;
import Enum.TypeEnum.ActType;
import Errors.ActException;
import Map.Door;
import Enum.*;

import java.util.ArrayList;
import java.util.List;

public class Move extends Act {
    protected Door door;
    public Move(Door door) {
        super(1, ActType.MOVE, "Движение");
        this.door = door;
    }

    @Override
    public void doAct() throws ActException {
        try {
            if (this.door.isClosed()) this.door.open(this.creature.getStat(Stats.STRENGTH));
        } catch (LocationException e) {
            throw new ActException(e.getMessage());
        }
        System.out.println(this.creature.getName() + " двигается в " + this.door.getWithOut(this.creature.getLocation()).getName());
        this.creature.setLocation(this.door.getWithOut(this.creature.getLocation()));
    }

    @Override
    public String toString() {
        return "Move{" + super.toString() +
                ",door=" + door +
                '}';
    }
}
