package Entity.Characters;

import Acts.ActsToCreature.ActToCreature;
import Entity.Items.Instrumets.Instrument;
import Entity.Items.Clothes.Cloth;
import Map.Locations.Location;
import Errors.GameException;
import Errors.ActException;
import Entity.BodyParts.*;
import Entity.Items.Item;
import Effects.Effect;
import Acts.Act;
import Enum.*;

import java.util.*;

public class Human extends Creature {
    protected ArrayList<Item> items;
    protected HashMap<BodyPart, Cloth> clothes;
    protected ArrayList<Instrument> instruments;
    public Human(Location location) {
        super(new ArrayList<>(List.of(new Head(), new Body(), new Arm(), new Arm(), new Leg(), new Leg())), location, "Человек");
        this.instruments = new ArrayList<>();
        this.clothes = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    public void checkLocationItems() {
        if (this.getStat(Stats.FORESIGHT) >= Math.random() * 100 && this.instruments.stream().filter(Instrument::isNeedFix).toList().size() > 0) {
            ArrayList<Instrument> toFix = new ArrayList<>();
            ArrayList<Purpose> toFind = new ArrayList<>();

            for (Instrument instrument : this.instruments) {
                if (instrument.isNeedFix() && this.getStat(Stats.INTELLIGENCE) >= Math.random() * 100) {
                    toFix.add(instrument);
                    if (!toFind.contains(instrument.getNeed()) && (!items.stream().map(Item::getPurpose).toList().contains(instrument.getNeed()) || this.getStat(Stats.RISK) >= Math.random() * 100)) toFind.add(instrument.getNeed());
                }
            }

            for (Item item: this.location.getItems()) {
                if ((item.getPurpose().equals(Purpose.HEAL) || toFind.contains(item.getPurpose())) && this.getStat(Stats.INTELLIGENCE) >= Math.random() * 100) {
                    this.items.add(this.location.getItem(item));
                }
            }

            if (toFix.size() > 0) {
                Instrument instrumentToFix = toFix.get(new Random().nextInt(toFix.size()));
                ArrayList<Item> itemsNeed = new ArrayList<>(this.items.stream().filter(item -> item.getPurpose().equals(instrumentToFix.getNeed())).toList());
                if (itemsNeed.size() > 0) {
                    System.out.printf("%s добавил в %s %s!\n", this.getName(), instrumentToFix.getName(), itemsNeed.get(0).getName());
                    instrumentToFix.addNeed(itemsNeed.get(0));
                    this.items.remove(itemsNeed.get(0));
                    this.moves -= 1;
                }
            }
        }
    }

    @Override
    public void addEffect(Effect effect) {
        if (this.clothes.containsKey(effect.getBodyPart())) {
            effect.setKf(this.clothes.get(effect.getBodyPart()).getKf());
        }
        super.addEffect(effect);
    }

    @Override
    public Act prepareAct() throws ActException {
        if (this.status.equals(Status.CRITICAL) && this.getStat(Stats.SUSTAINABILITY) <= Math.random() * 100) {
            System.out.println("Решает бежать");
            try {
                return this.prepareMove();
            } catch (GameException ignored) {
            }
        }

        this.checkLocationItems();

        try {
            return this.prepareActWith();
        } catch (GameException e) {
            try {
                return this.prepareMove();
            } catch (GameException e1) {
                throw new ActException(e1.getMessage());
            }
        }
    }

    @Override
    public Act prepareAttack(Creature creature) {
        ArrayList<Instrument> instruments = new ArrayList<>(this.instruments.stream().filter(instrument -> instrument.getPurpose().equals(Purpose.DAMAGE) && (instrument.isReady() || this.getStat(Stats.INTELLIGENCE) <= Math.random() * 100)).toList());
        if (instruments.size() > 0 && this.getStat(Stats.INTELLIGENCE) >= Math.random() * 100) {
            if (this.getStat(Stats.RISK) >= Math.random() * 100) {
                ActToCreature attack = instruments.stream().sorted(Comparator.comparing(Instrument::getChangeHP).reversed()).toList().get(this.checkIntelligence(instruments.size())).getAct();
                attack.applyCreature(this);
                attack.applyCreatureTo(creature);
                return attack;
            }
            if (this.getStat(Stats.RISK) <= Math.random() * 100) {
                ActToCreature attack = instruments.stream().sorted(Comparator.comparing(Instrument::getEfficacy).reversed()).toList().get(this.checkIntelligence(instruments.size())).getAct();
                attack.applyCreature(this);
                attack.applyCreatureTo(creature);
                return attack;
            }
            ActToCreature attack = instruments.stream().sorted(Comparator.comparing(Instrument::getAccuracy).reversed()).toList().get(this.checkIntelligence(instruments.size())).getAct();
            attack.applyCreature(this);
            attack.applyCreatureTo(creature);
            return attack;
        }
        return super.prepareAttack(creature);
    }

    @Override
    public String toString() {
        return "Human{" + super.toString() +
                ",items=" + items +
                ",clothes=" + clothes +
                ",instruments=" + instruments +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), items, clothes, instruments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Human human = (Human) o;
        return Objects.equals(items, human.items) && Objects.equals(clothes, human.clothes) && Objects.equals(instruments, human.instruments);
    }
}
