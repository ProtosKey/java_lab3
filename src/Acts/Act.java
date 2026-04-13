package Acts;

import Entity.Characters.Creature;
import Enum.TypeEnum.ObjectType;
import Enum.TypeEnum.ActType;
import Errors.ActException;
import Basic.ObjectID;

import java.util.Objects;

public abstract class Act extends ObjectID {
    protected int cost;
    protected ActType actType;
    protected Creature creature;

    public Act(ActType actType, String name) {
        super(ObjectType.ACT, name);
        this.cost = 1;
        this.actType = actType;
    }

    public Act(int cost, ActType actType, String name) {
        super(ObjectType.ACT, name);
        this.cost = cost;
        this.actType = actType;
    }

    public void applyCreature(Creature creature) {
        this.creature = creature;
    }

    public int getCost() {
        return cost;
    }

    public Creature getCreature() {
        return creature;
    }

    public abstract void doAct() throws ActException;

    @Override
    public String toString() {
        return "Act{" + super.toString() +
                ",cost=" + cost +
                ",actType=" + actType +
                ",creature=" + creature +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cost, actType, creature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Act act = (Act) o;
        return cost == act.cost && actType == act.actType && Objects.equals(creature, act.creature);
    }
}
