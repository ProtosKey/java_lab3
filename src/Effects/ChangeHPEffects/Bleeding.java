package Effects.ChangeHPEffects;

import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.EffectType;
import Errors.EffectException;

public class Bleeding extends ChangeHPEffect {
    public Bleeding(BodyPart bodyPart) {
        super(bodyPart, -30, EffectType.DAMAGE, "Кровотечение", 3);
    }

    @Override
    public void doEffect() throws EffectException {
        System.out.printf("%s!\n", this.getName());
        this.bodyPart.changeHP(Math.round(this.getChangeHP() * this.kf));
        if (--this.moves == 0) throw new EffectException(String.format("Действие %s закончилось", this.getName()));
    }
}
