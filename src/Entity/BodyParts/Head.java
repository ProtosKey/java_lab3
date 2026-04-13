package Entity.BodyParts;

import Enum.TypeEnum.BodyPartType;

public class Head extends BodyPart {
    public Head() {
        super(50, 50, "Голова", BodyPartType.NECESSARY);
    }

    @Override
    public String toString() {
        return "Head{" + super.toString() + "}";
    }
}
