package Acts.ActsToCreature;

import Acts.Act;
import Entity.Characters.Creature;
import Enum.TypeEnum.ActType;

import java.util.Objects;

public abstract class ActToCreature extends Act {
    protected Creature creatureTo;
    public ActToCreature(ActType actType, String name) {
        super(actType, name);
    }
    public ActToCreature(int cost, ActType actType, String name) {
        super(cost, actType, name);
    }

    public void applyCreatureTo(Creature creatureTo) {
        this.creatureTo = creatureTo;
    }

    public abstract void addEffect();

    @Override
    public String toString() {
        return "ActToCreature{" + super.toString() +
                ",creatureTo=" + creatureTo +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creatureTo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ActToCreature that = (ActToCreature) o;
        return Objects.equals(creatureTo, that.creatureTo);
    }
}
