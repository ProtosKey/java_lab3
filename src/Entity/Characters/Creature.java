package Entity.Characters;

import Acts.ActsToCreature.ActToCreature;
import Acts.ActsToCreature.Attacks.Attack;
import Acts.ActsToCreature.Heals.Heal;
import Entity.BodyParts.BodyPart;
import Enum.TypeEnum.BodyPartType;
import Enum.TypeEnum.ObjectType;
import Errors.EffectException;
import Map.Locations.Location;
import Errors.DeadException;
import Errors.GameException;
import Errors.ActException;
import Simulator.Simulator;
import Basic.ObjectID;
import Effects.Effect;
import Acts.*;
import Enum.*;
import Map.*;

import java.util.*;

public class Creature extends ObjectID implements Comparable<Creature> {
    private static final class Memory extends ObjectID {
        private ArrayList<Integer> friends;
        private ArrayList<Integer> enemies;
        private final HashMap<Integer, Integer> seen;
        private final HashMap<Location, Integer> locations;

        public Memory() {
            super(ObjectType.MEMORY, "Память");
            this.friends = new ArrayList<>();
            this.enemies = new ArrayList<>();
            this.locations = new HashMap<>();
            this.seen = new HashMap<>();
        }

        public HashMap<Integer, Integer> getSeen() {
            return seen;
        }

        public ArrayList<Integer> getFriends() {
            return friends;
        }

        public ArrayList<Integer> getEnemies() {
            return enemies;
        }

        public void addLocation(Location location, int moment) {
            this.locations.put(location, moment);
        }

        public void setFriends(ArrayList<Integer> friends) {
            this.friends = friends;
        }

        public void setEnemies(ArrayList<Integer> enemies) {
            this.enemies = enemies;
        }

        public int getMomentLocation(Location location) {
            return this.locations.get(location);
        }

        public boolean checkLocation(Location location) {
            return this.locations.containsKey(location);
        }

        @Override
        public String toString() {
            return "Memory{" + super.toString() +
                    ",friends=" + friends +
                    ",enemies=" + enemies +
                    ",locations=" + locations.values() +
                    ",seen=" + seen.values() +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), friends, enemies, locations.keySet().stream().map(ObjectID::getId).toList(), locations.values());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Memory memory = (Memory) o;
            return Objects.equals(friends, memory.friends) && Objects.equals(enemies, memory.enemies) && Objects.equals(locations, memory.locations) && Objects.equals(seen, memory.seen);
        }
    }

    protected int moves;
    protected Status status;
    protected Memory memory;
    private int[] stats;
    protected Location location;
    protected ArrayList<Effect> effects;
    protected ArrayList<BodyPart> bodyParts;

    public Creature(ArrayList<BodyPart> bodyParts, Location location, String name) {
        super(ObjectType.CREATURE, name);
        this.moves = 0;
        this.location = location;
        this.status = Status.GOOD;
        this.bodyParts = bodyParts;
        this.memory = new Memory();
        this.location.addCreature(this);
        this.effects = new ArrayList<>();
        this.memory.addLocation(this.location, Simulator.getTeak());
        this.stats = new int[]{90, 50, 50, 70, 50, 80, 100, 10, 50};
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public boolean isAlive() {
        this.checkNeeds();
        return !status.equals(Status.DEAD);
    }

    public int getStat(Stats stat) {
        return stats[stat.ordinal()];
    }

    public Location getLocation() {
        return location;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Integer> getFriends() {
        return this.memory.getFriends();
    }

    public ArrayList<Integer> getEnemies() {
        return this.memory.getEnemies();
    }

    public ArrayList<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setLocation(Location location) {
        this.memory.addLocation(location, Simulator.getTeak());
        this.location.removeCreature(this);
        this.location = location;
        this.location.addCreature(this);
    }

    public void setFriends(ArrayList<Creature> friends) {
        this.memory.setFriends(new ArrayList<>(friends.stream().map(ObjectID::getId).toList()));
    }

    public void setEnemies(ArrayList<Creature> enemies) {
        this.memory.setEnemies(new ArrayList<>(enemies.stream().map(ObjectID::getId).toList()));
    }

    public boolean checkFriend(Creature creature) {
        return this.memory.getFriends().contains(creature.getId());
    }

    public boolean checkEnemy(Creature creature) {
        return this.memory.getEnemies().contains(creature.getId());
    }

    public void addEffect(Effect effect) {
        try {
            effect.doEffect();
            this.effects.add(effect);
        } catch (EffectException ignored) {
        }
    }

    public void checkNeeds() {
        int countHealth = 0;
        for (BodyPart bodyPart: this.bodyParts) {
            countHealth += (bodyPart.checkDead() ? 3 : (bodyPart.checkCriticalHelp() ? 2 : bodyPart.checkHelp() ? 1 : 0)) * (bodyPart.isNecessary() ? 2 : 1);
        }
        this.status = countHealth >= 6 ? Status.DEAD : countHealth == 5 ? Status.CRITICAL : countHealth >= 3 ? Status.HELP : countHealth >= 1 ? Status.HURT : Status.GOOD;
    }

    private void startTeak() {
        this.moves += 6;
    }

    public void makeTeak() throws DeadException {
        if (this.isAlive()) {
            this.startTeak();
            this.checkEffects();
            try {
                while (this.moves >= 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }

                    this.checkNeeds();
                    prepareAct().doAct();
                }
            } catch (ActException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Act prepareAct() throws ActException {
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

    public void check(BodyPartType bodyPartType) throws GameException {
        if (this.bodyParts.stream().filter(bodyPart -> bodyPart.getBodyPartType().equals(bodyPartType)).mapToInt(bodypart -> bodypart.checkDead() ? 0 : 1).sum() == 0) {
            throw new GameException();
        }
    }

    public void checkEffects() {
        ArrayList<Effect> toDelete = new ArrayList<>();
        for (Effect effect: this.effects) {
            try {
                effect.doEffect();
            } catch (EffectException ignored) {
                toDelete.add(effect);
            }
        }
        toDelete.removeAll(toDelete);
    }

    public Act prepareActWith() throws ActException, GameException {
        if (this.status.equals(Status.CRITICAL) || (this.status.equals(Status.HELP) || this.status.equals(Status.HURT)) && Math.random() * 100 <= this.stats[Stats.SELFISHNESS.ordinal()]) {
            ActToCreature heal = new Heal();
            heal.applyCreature(this);
            heal.applyCreatureTo(this);
            if (heal.getCost() <= this.moves && Math.random() * 100 <= this.stats[Stats.SELFISHNESS.ordinal()]) {
                this.moves -= heal.getCost();
                return heal;
            }
        }

        this.check(BodyPartType.USEFUL);

        ArrayList<Creature> toHelp = new ArrayList<>();
        ArrayList<Creature> toKill = new ArrayList<>();

        for (Creature creature: this.location.getCreatures()) {
            creature.checkNeeds();
            if (creature.getId() == this.getId() || !creature.isAlive()) continue;
            if (this.checkFriend(creature) && (creature.getStatus().equals(Status.HELP) || creature.getStatus().equals(Status.CRITICAL))) toHelp.add(creature);
            if (this.checkEnemy(creature) && (this.memory.getSeen().containsKey(creature.getId()) && this.memory.getSeen().get(creature.getId()) <= this.stats[Stats.MEMORY.ordinal()] || creature.getStat(Stats.STEALTH) >= Math.random() * 100)) {
                this.memory.getSeen().put(creature.getId(), Simulator.getTeak());
                toKill.add(creature);
            }
        }

        Collections.shuffle(toHelp);
        Collections.shuffle(toKill);

        toHelp.sort(Comparator.comparing(Creature::getStatus).reversed());
        toKill.sort(Comparator.comparing(Creature::getStatus).reversed());

        if (toHelp.size() > 0 && toKill.size() > 0) {
            Act heal = prepareHeal(toHelp.get(this.checkIntelligence(toHelp.size())));
            if (this.stats[Stats.KINDNESS.ordinal()] > Math.random() * 100 && heal.getCost() <= this.moves) {
                this.moves -= heal.getCost(); return heal;
            }
            Act attack = prepareAttack(toKill.get(this.checkIntelligence(toKill.size())));
            if (attack.getCost() <= this.moves) {
                this.moves -= attack.getCost(); return attack;
            }
            throw new ActException("Закончились очки");
        } else if (toHelp.size() > 0) {
            Act heal = prepareHeal(toHelp.get(this.checkIntelligence(toHelp.size())));
            if (heal.getCost() <= this.moves) {
                this.moves -= heal.getCost(); return heal;
            } else throw new ActException("Закончились очки");
        } else if (toKill.size() > 0) {
            Act attack = prepareAttack(toKill.get(this.checkIntelligence(toKill.size())));
            if (attack.getCost() <= this.moves) {
                this.moves -= attack.getCost(); return attack;
            } else throw new ActException("Закончились очки");
        }
        throw new GameException();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    protected boolean checkFriendsOrEnemies(Location location) {
        return location.getCreatures().stream().filter(creature -> this.memory.getEnemies().contains(creature.getId())).toList().size() > 0 ||
                location.getCreatures().stream().filter(creature -> this.memory.getFriends().contains(creature.getId())).toList().size() > 0;
        //  || creature.getStatus().equals(Status.CRITICAL) || creature.getStatus().equals(Status.HELP) - на случай жестокой проверки
    }

    public Act prepareMove() throws ActException, GameException {
        this.check(BodyPartType.MOTIVE);

        ArrayList<Door> toHelp = new ArrayList<>();
        ArrayList<Door> toExplore = new ArrayList<>();
        ArrayList<Door> toMove = new ArrayList<>();

        for (Door door: this.location.getWhereLocations()) {
            Location locationNext = door.getWithOut(this.location);
            if (!door.isClosed() && this.checkFriendsOrEnemies(locationNext)) toHelp.add(door);
            if (!this.memory.checkLocation(locationNext) || door.isClosed()) toExplore.add(door);
            toMove.add(door);
        }

        if (toExplore.size() > 0 || toHelp.size() > 0) {
            if (toExplore.size() > 0 && toHelp.size() > 0) {
                if (this.stats[Stats.KINDNESS.ordinal()] >= Math.random() * 100) {
                    Act move = new Move(toExplore.get(new Random().nextInt(toExplore.size())));
                    move.applyCreature(this);
                    if (move.getCost() <= this.moves) {
                        this.moves -= move.getCost();
                        return move;
                    } else throw new ActException("Закончились очки");
                } else {
                    Act move = new Move(toHelp.get(new Random().nextInt(toHelp.size())));
                    move.applyCreature(this);
                    if (move.getCost() <= this.moves) {
                        this.moves -= move.getCost();
                        return move;
                    } else throw new ActException("Закончились очки");
                }
            } else if (toExplore.size() > 0) {
                Act move = new Move(toExplore.get(new Random().nextInt(toExplore.size())));
                move.applyCreature(this);
                if (move.getCost() <= this.moves) {
                    this.moves -= move.getCost();
                    return move;
                } else throw new ActException("Закончились очки");
            } else {
                Act move = new Move(toHelp.get(new Random().nextInt(toHelp.size())));
                move.applyCreature(this);
                if (move.getCost() <= this.moves) {
                    this.moves -= move.getCost();
                    return move;
                } else throw new ActException("Закончились очки");
            }
        } else if (toMove.size() > 0) {
            toMove.sort(Comparator.comparingInt(door -> this.memory.getMomentLocation(door.getWithOut(this.location))));
            Act move = new Move(toMove.get(this.checkIntelligence(toMove.size())));
            move.applyCreature(this);
            if (move.getCost() <= this.moves) {
                this.moves -= move.getCost();
                return move;
            } else throw new ActException("Закончились очки");
        }

        throw new ActException("Закончились очки");
    }

    public int checkIntelligence(int count) {
        return this.stats[Stats.INTELLIGENCE.ordinal()] >= Math.random() * 100 ? 0 : new Random().nextInt(count);
    }

    public BodyPart getNeedHelpBodyPart() {
        return new ArrayList<>(this.bodyParts).stream().sorted(Comparator.comparing(BodyPart::getHP).thenComparing(BodyPart::getBodyPartType)).toList().get(0);
    }

    public Act prepareAttack(Creature creature) {
        ActToCreature attack = new Attack();
        attack.applyCreature(this);
        attack.applyCreatureTo(creature);
        return attack;
    }

    public Act prepareHeal(Creature creature) {
        ActToCreature heal = new Heal();
        heal.applyCreature(this);
        heal.applyCreatureTo(creature);
        return heal;
    }

    @Override
    public int compareTo(Creature creature) {
        this.checkNeeds();
        return this.status.ordinal() - creature.getStatus().ordinal();
    }

    @Override
    public String toString() {
        return "Creature{" + super.toString() +
                ",moves=" + moves +
                ",status=" + status +
                ",memory=" + memory +
                ",stats=" + Arrays.toString(stats) +
                ",location=" + location.getName() +
                ",effects=" + effects +
                ",bodyParts=" + bodyParts +
                '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), moves, status, memory, location.getId(), effects, bodyParts);
        result = 31 * result + Arrays.hashCode(stats);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Creature creature = (Creature) o;
        return moves == creature.moves && status == creature.status && Objects.equals(memory, creature.memory) && Arrays.equals(stats, creature.stats) && Objects.equals(location, creature.location) && Objects.equals(effects, creature.effects) && Objects.equals(bodyParts, creature.bodyParts);
    }
}
