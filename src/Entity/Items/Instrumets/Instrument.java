package Entity.Items.Instrumets;

import Acts.ActsToCreature.ActToCreature;
import Errors.ItemException;
import Entity.Items.Item;
import Enum.Purpose;

import java.util.ArrayList;
import java.util.Random;

public abstract class Instrument extends Item {
    protected Purpose need;
    protected int changeHP;
    protected float accuracy;
    protected ArrayList<ActToCreature> actCases;

    public Instrument(int changeHP, float accuracy, Purpose purpose, String name, Purpose need) {
        super(purpose, name);
        this.need = need;
        this.changeHP = changeHP;
        this.actCases = new ArrayList<>();
        this.accuracy = Math.max(Math.min(accuracy, 1), 0);
    }

    public void setActCases(ArrayList<ActToCreature> actCases) {
        this.actCases = actCases;
    }

    public ActToCreature getAct() {
        return this.actCases.get(new Random().nextInt(this.actCases.size()));
    }

    public int getEfficacy() {
        return Math.round(this.changeHP * this.accuracy);
    }

    public int getChangeHP() {
        return changeHP;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public abstract boolean isReady();

    public abstract boolean isNeedFix();

    public Purpose getNeed() {
        return this.need;
    }

    public abstract void addNeed(Item item);

    public int use() throws ItemException {
        if (this.accuracy > Math.random()) {
            return this.changeHP;
        } else {
            throw new ItemException("Не попал");
        }
    }

    @Override
    public String toString() {
        return "Instrument{" + super.toString() +
                ",need=" + need +
                ",changeHP=" + changeHP +
                ",accuracy=" + accuracy +
                ",actCases=" + actCases +
                '}';
    }
}
