package Map.Locations;

import Basic.Interfaces.HaveInside;
import Entity.Characters.Creature;
import Enum.TypeEnum.ObjectType;
import Entity.Items.Item;
import Basic.ObjectID;
import Errors.LocationException;
import Map.*;

import java.util.*;

public class Location extends ObjectID implements HaveInside {
    protected ArrayList<Item> items;
    protected ArrayList<Creature> creatures;
    protected ArrayList<Door> whereLocations;

    public Location(String name) {
        super(ObjectType.LOCATION, name);
        this.items = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.whereLocations = new ArrayList<>();
    }

    public ArrayList<Creature> getCreatures() {
        return this.creatures;
    }

    public ArrayList<Door> getWhereLocations() {
        return whereLocations;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addWhereLocation(Door door) {
        this.whereLocations.add(door);
    }

    public void addCreature(Creature creature) {
        if (!this.creatures.contains(creature)) this.creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        this.creatures.remove(creature);
    }

    @Override
    public ArrayList<Item> getItems() {
        return this.items;
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public Item getItem(Item item) {
        this.items.remove(item);
        return item;
    }

    @Override
    public String toString() {
        return "Location{" + super.toString() +
                ",items=" + items.stream().map(ObjectID::getName).toList() +
                ",creatures=" + creatures.stream().map(ObjectID::getId).toList() +
                ",whereLocations=" + whereLocations.stream().map(ObjectID::getId).toList() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), items, creatures, whereLocations.stream().map(ObjectID::getId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return Objects.equals(items, location.items) && Objects.equals(creatures, location.creatures) && Objects.equals(whereLocations, location.whereLocations);
    }
}
