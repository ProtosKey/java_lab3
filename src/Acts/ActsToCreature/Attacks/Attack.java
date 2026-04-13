package Acts.ActsToCreature.Attacks;

import Effects.ChangeHPEffects.DamageEffect;
import Acts.ActsToCreature.ActToCreature;
import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.ActType;

import java.util.Random;

public class Attack extends ActToCreature {
    public Attack() {
        super(2, ActType.ATTACK, "Атака");
    }

    public Attack(int cost) {
        super(cost, ActType.ATTACK, "Атака");
    }

    @Override
    public void addEffect() {
        BodyPart bodyPart = creatureTo.getBodyParts().get(new Random().nextInt(creatureTo.getBodyParts().size()));
        creatureTo.addEffect(new DamageEffect(bodyPart));
        System.out.printf("Попадает по %s\n", bodyPart.getName());
    }

    @Override
    public void doAct() {
        System.out.println(this.creature.getName() + " бьет " + this.creatureTo.getName());
        this.addEffect();
    }

    @Override
    public String toString() {
        return "Attack{" + super.toString() +
                '}';
    }
}
