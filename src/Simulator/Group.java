package Simulator;

import Entity.Characters.Creature;
import Enum.TypeEnum.ObjectType;
import Basic.ObjectID;

import java.util.ArrayList;
import java.util.Objects;

public class Group extends ObjectID {
    private final String name;
    protected ArrayList<Creature> group;

    public Group(ArrayList<Creature> creatures, String name) {
        super(ObjectType.GROUP, name);
        this.group = creatures;
        this.name = name;
    }

    public boolean isAlive() {
        boolean check = false;

        for(Creature creature: this.group) {
            check = creature.isAlive() || check;
        }
        return check;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Creature> getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Group{" + super.toString() +
                ",name=" + name + '\'' +
                ",group=" + group.stream().map(ObjectID::getId).toList() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group1 = (Group) o;
        return Objects.equals(name, group1.name) && Objects.equals(group, group1.group);
    }
}
