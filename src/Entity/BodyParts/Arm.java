package Entity.BodyParts;

import Enum.TypeEnum.BodyPartType;

public class Arm extends BodyPart {
    public Arm() {
        super(100, 100, "Рука", BodyPartType.USEFUL);
    }

    @Override
    public String toString() {
        return "Arm{" + super.toString() + "}";
    }
}
