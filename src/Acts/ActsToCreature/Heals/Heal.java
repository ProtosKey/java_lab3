package Acts.ActsToCreature.Heals;

import Acts.ActsToCreature.ActToCreature;
import Effects.ChangeHPEffects.HealEffect;
import Enum.TypeEnum.ActType;

public class Heal extends ActToCreature {
    public Heal() {
        super(3, ActType.HEAL, "Лечение");
    }

    @Override
    public void addEffect() {
        creatureTo.addEffect(new HealEffect(creatureTo.getNeedHelpBodyPart()));
    }

    @Override
    public void doAct() {
        if (creature.getId() == creatureTo.getId()) {
            System.out.println(creature.getName() + " лечит " + creature.getNeedHelpBodyPart().getName());
        } else System.out.println(creature.getName() + " лечит у " + creature.getName() + " " + creature.getNeedHelpBodyPart().getName());
        this.addEffect();
    }

    @Override
    public String toString() {
        return "Heal{" + super.toString() +
                '}';
    }
}
