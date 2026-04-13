package Entity.BodyParts;

import Enum.TypeEnum.BodyPartType;
import Enum.TypeEnum.ObjectType;
import Basic.ObjectID;

import java.util.Objects;

public class BodyPart extends ObjectID {
    protected int hP;
    protected int maxHP;
    private final BodyPartType bodyPartType;

    public BodyPart(int maxHP, int hP, String name, BodyPartType bodyPartType) {
        super(ObjectType.BODY_PART, name);
        this.hP = hP;
        this.maxHP = Math.max(this.hP, maxHP);
        this.bodyPartType = bodyPartType;
    }

    public boolean isNecessary() {
        return this.bodyPartType.equals(BodyPartType.NECESSARY);
    }

    public int getHP() {
        return this.hP;
    }

    public BodyPartType getBodyPartType() {
        return bodyPartType;
    }

    public void changeHP(int change) {
        this.hP = Math.max(Math.min(this.maxHP, this.hP + change), 0);
    }

    public boolean checkDead() {
        return this.hP == 0;
    }

    public boolean checkCriticalHelp() {
        if (this.checkDead()) return true;
        return this.maxHP / this.hP > 5 || this.hP < 20;
    }

    public boolean checkHelp() {
        if (this.checkDead()) return true;
        return this.maxHP / this.hP > 1;
    }

    @Override
    public String toString() {
        return "BodyPart{" + super.toString() +
                ",hP=" + hP +
                ",maxHP=" + maxHP +
                ",bodyPartType=" + bodyPartType +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hP, maxHP, bodyPartType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BodyPart bodyPart = (BodyPart) o;
        return hP == bodyPart.hP && maxHP == bodyPart.maxHP && bodyPartType == bodyPart.bodyPartType;
    }
}
