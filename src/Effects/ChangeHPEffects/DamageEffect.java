package Effects.ChangeHPEffects;

import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.EffectType;

public class DamageEffect extends ChangeHPEffect {
    public DamageEffect(BodyPart bodyPart) {
        super(bodyPart, -30, EffectType.DAMAGE, "Эффект урона", 1);
    }

    public DamageEffect(BodyPart bodyPart, int changeHP) {
        super(bodyPart, changeHP, EffectType.DAMAGE, "Эффект урона", 1);
    }

    @Override
    public String toString() {
        return "DamageEffect{" + super.toString() + "}";
    }
}
