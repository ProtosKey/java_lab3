package Effects;

import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.ObjectType;
import Enum.TypeEnum.EffectType;
import Errors.EffectException;
import Basic.ObjectID;

import java.util.Objects;

public abstract class Effect extends ObjectID {
    protected float kf;
    protected int moves;
    protected BodyPart bodyPart;
    protected EffectType effectType;

    public Effect(BodyPart bodyPart, EffectType effectType, String name, int moves) {
        super(ObjectType.EFFECT, name);
        this.bodyPart = bodyPart;
        this.effectType = effectType;
        this.moves = moves;
        this.kf = 1;
    }

    public void setKf(float kf) {
        this.kf = kf;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public abstract void doEffect() throws EffectException;

    @Override
    public String toString() {
        return "Effect{" + super.toString() +
                ",kf=" + kf +
                ",bodyPart=" + bodyPart +
                ",effectType=" + effectType +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kf, bodyPart, effectType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Effect effect = (Effect) o;
        return Float.compare(effect.kf, kf) == 0 && Objects.equals(bodyPart, effect.bodyPart) && effectType == effect.effectType;
    }
}
