package Entity.Characters;

import Entity.Items.Clothes.Trousers;
import Entity.Items.Clothes.Cloth;
import Entity.Items.Clothes.Shirt;
import Entity.Items.Clothes.Cap;
import Entity.Items.Instrumets.Handgun;
import Entity.Items.Item;
import Entity.Items.Parts.Round;
import Errors.ItemException;
import Map.Locations.Location;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Sailor extends Human {
    public Sailor(Location location) {
        super(location);
        ArrayList<Cloth> clothes = new ArrayList<>(List.of(new Cap(), new Shirt(), new Trousers()));
        this.clothes.put(this.bodyParts.get(0), clothes.get(0));
        this.clothes.put(this.bodyParts.get(1), clothes.get(1));
        this.clothes.put(this.bodyParts.get(4), clothes.get(2));
        this.clothes.put(this.bodyParts.get(5), clothes.get(2));
        Handgun handgun = new Handgun();
        try {
            ArrayList<Item> rounds = new ArrayList<>();
            for (int i = 0; i <= new Random().nextInt(4, 12); i++) rounds.add(new Round());
            handgun.setItems(rounds);
            for (int i = 0; i <= new Random().nextInt(2, 4); i++) this.addItem(new Round());
        } catch (ItemException ignored) {
        } finally {
            this.addInstrument(handgun);
        }
    }

    @Override
    public String toString() {
        return "Sailor{" + super.toString() + "}";
    }
}
