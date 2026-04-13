package Acts.ActsToCreature.Attacks;

import Effects.ChangeHPEffects.Bleeding;
import Effects.ChangeHPEffects.DamageEffect;
import Entity.BodyParts.BodyPart;
import Entity.Items.Instrumets.Weapon;
import Errors.ItemException;

import java.util.Random;

public class AttackWithRifle extends Attack {
    protected Weapon weapon;
    public AttackWithRifle(int cost, Weapon weapon) {
        super(cost);
        this.weapon = weapon;
    }

    @Override
    public void addEffect() {
        try {
            BodyPart bodyPart = creatureTo.getBodyParts().get(new Random().nextInt(creatureTo.getBodyParts().size()));
            creatureTo.addEffect(new DamageEffect(bodyPart, this.weapon.use()));
            if (Math.random() <= 0.3) creatureTo.addEffect(new Bleeding(bodyPart));
            System.out.printf("Простреливает %s\n", bodyPart.getName());
        } catch (ItemException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void doAct() {
        System.out.printf("%s целится в %s... ", this.creature.getName(), this.creatureTo.getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        this.addEffect();
    }
}
