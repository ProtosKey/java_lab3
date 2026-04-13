package Entity.Characters;

import Entity.Items.Clothes.Cap;
import Entity.Items.Clothes.Cloth;
import Entity.Items.Clothes.Shirt;
import Entity.Items.Clothes.Trousers;
import Entity.Items.Instrumets.Winchester;
import Entity.Items.Item;
import Entity.Items.Parts.Round;
import Errors.ItemException;
import Map.Locations.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Captain extends Human {
    public Captain(Location location) {
        super(location);
        ArrayList<Cloth> clothes = new ArrayList<>(List.of(new Cap(), new Shirt(), new Trousers()));
        this.clothes.put(this.bodyParts.get(0), clothes.get(0));
        this.clothes.put(this.bodyParts.get(1), clothes.get(1));
        this.clothes.put(this.bodyParts.get(4), clothes.get(2));
        this.clothes.put(this.bodyParts.get(5), clothes.get(2));
        Winchester winchester = new Winchester();
        try {
            ArrayList<Item> rounds = new ArrayList<>();
            for (int i = 0; i <= new Random().nextInt(2, 7); i++) rounds.add(new Round());
            winchester.setItems(rounds);
            for (int i = 0; i <= new Random().nextInt(2, 4); i++) this.addItem(new Round());
        } catch (ItemException ignored) {
        } finally {
            this.addInstrument(winchester);
        }
        this.setStats(new int[]{100, 80, 60, 30, 40, 100, 70, 20, 60});
    }

    @Override
    public String toString() {
        return "Captain{" + super.toString() +"}";
    }
}
