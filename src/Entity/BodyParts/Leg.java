package Entity.BodyParts;

import Enum.TypeEnum.BodyPartType;

public class Leg extends BodyPart {
    public Leg() {
        super(150, 150, "Нога", BodyPartType.MOTIVE);
    }

    @Override
    public String toString() {
        return "Leg{" + super.toString() + "}";
    }
}
