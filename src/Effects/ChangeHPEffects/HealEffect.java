package Effects.ChangeHPEffects;

import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.EffectType;

public class HealEffect extends ChangeHPEffect {
    public HealEffect(BodyPart bodyPart) {
        super(bodyPart, 20, EffectType.HEAL, "Эффект лечения", 1);
    }
    public HealEffect(BodyPart bodyPart, int changeHP) {
        super(bodyPart, changeHP, EffectType.HEAL, "Эффект лечения", 1);
    }

    @Override
    public String toString() {
        return "HealEffect{" + super.toString() + "}";
    }
}
