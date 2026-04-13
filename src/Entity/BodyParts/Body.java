package Entity.BodyParts;

import Enum.TypeEnum.BodyPartType;

public class Body extends BodyPart {
    public Body() {
        super(200, 200, "Тело", BodyPartType.NECESSARY);
    }

    @Override
    public String toString() {
        return "Body{" + super.toString() + "}";
    }
}
