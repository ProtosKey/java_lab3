package Effects.ChangeHPEffects;

import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.EffectType;
import Effects.Effect;
import Errors.EffectException;

import java.util.Objects;

public class ChangeHPEffect extends Effect {
    private final int changeHP;
    public ChangeHPEffect(BodyPart bodyPart, int changeHP, EffectType effectType, String name, int moves) {
        super(bodyPart, effectType, name, moves);
        this.changeHP = changeHP;
    }

    public int getChangeHP() {
        return changeHP;
    }

    @Override
    public void doEffect() throws EffectException {
        this.bodyPart.changeHP(Math.round(changeHP * this.kf));
        if (--this.moves == 0) throw new EffectException();
    }

    @Override
    public String toString() {
        return "ChangeHPEffect{" + super.toString() +
                ",changeHP=" + changeHP +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), changeHP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChangeHPEffect that = (ChangeHPEffect) o;
        return changeHP == that.changeHP;
    }
}
