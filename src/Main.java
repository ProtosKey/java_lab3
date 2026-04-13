import Simulator.Simulator;
import Entity.Characters.*;
import Map.Locations.*;
import Simulator.*;
import Map.Map;
import Map.*;

import java.util.*;

public class Main {
    public static Map map;
    public static ArrayList<Creature> creatures = new ArrayList<>();

    public static void main(String[] args) {
        makeMap();

        Human captain = new Captain(map.getLocation(new Random().nextInt(6)));

        Human sailor1 = new Sailor(map.getLocation(new Random().nextInt(6)));
        Human sailor2 = new Sailor(map.getLocation(new Random().nextInt(6)));
        Human sailor3 = new Sailor(map.getLocation(new Random().nextInt(6)));
        Human sailor4 = new Sailor(map.getLocation(new Random().nextInt(6)));

        Human bandit1 = new Bandit(map.getLocation(new Random().nextInt(6)));
        Human bandit2 = new Bandit(map.getLocation(new Random().nextInt(6)));
        Human bandit3 = new Bandit(map.getLocation(new Random().nextInt(6)));
        Human bandit4 = new Bandit(map.getLocation(new Random().nextInt(6)));
        Human bandit5 = new Bandit(map.getLocation(new Random().nextInt(6)));

        captain.setName("Капитан");
        sailor1.setName("Матрос1");
        sailor2.setName("Матрос2");
        sailor3.setName("Матрос3");
        sailor4.setName("Матрос4");

        bandit1.setName("Бандит1");
        bandit2.setName("Бандит2");
        bandit3.setName("Бандит3");
        bandit4.setName("Бандит4");
        bandit5.setName("Бандит5");

        captain.setEnemies(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4, bandit5)));
        sailor1.setEnemies(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4, bandit5)));
        sailor2.setEnemies(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4, bandit5)));
        sailor3.setEnemies(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4, bandit5)));
        sailor4.setEnemies(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4, bandit5)));

        bandit1.setEnemies(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3, sailor4)));
        bandit2.setEnemies(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3, sailor4)));
        bandit3.setEnemies(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3, sailor4)));
        bandit4.setEnemies(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3, sailor4)));
        bandit5.setEnemies(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3, sailor4)));

        captain.setFriends(new ArrayList<>(Arrays.asList(sailor1, sailor2, sailor3, sailor4)));
        sailor1.setFriends(new ArrayList<>(Arrays.asList(captain, sailor2, sailor3, sailor4)));
        sailor2.setFriends(new ArrayList<>(Arrays.asList(captain, sailor1, sailor3, sailor4)));
        sailor3.setFriends(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor4)));
        sailor4.setFriends(new ArrayList<>(Arrays.asList(captain, sailor1, sailor2, sailor3)));

        bandit1.setFriends(new ArrayList<>(Arrays.asList(bandit2, bandit3, bandit4, bandit5)));
        bandit2.setFriends(new ArrayList<>(Arrays.asList(bandit1, bandit3, bandit4, bandit5)));
        bandit3.setFriends(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit4, bandit5)));
        bandit4.setFriends(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit5)));
        bandit5.setFriends(new ArrayList<>(Arrays.asList(bandit1, bandit2, bandit3, bandit4)));

        creatures = new ArrayList<>(List.of(captain, sailor1, sailor2, sailor3, sailor4, bandit1, bandit2, bandit3, bandit4, bandit5));

        Simulator simulator = new Simulator(new ArrayList<>(Arrays.asList(
                new Group(new ArrayList<>(List.of(captain, sailor1, sailor2, sailor3, sailor4)), "Матросы"),
                new Group(new ArrayList<>(List.of(bandit1, bandit2, bandit3, bandit4, bandit5)), "Бандиты"))
        ));

        simulator.start();
    }

    public static void makeMap() {
        Location room1 = new Deck();
        Location room2 = new Cabin();
        Location room3 = new CommonRoom();
        Location room4 = new Kitchen();
        Location room5 = new Storage();
        Location room6 = new Dormitory();
        Location room7 = new Toilet();

        new Door(new Location[]{room1, room2}, 50);
        new Door(new Location[]{room1, room3}, 50);
        new Door(new Location[]{room3, room4}, 50);
        new Door(new Location[]{room3, room5}, 50);
        new Door(new Location[]{room3, room6}, 50);
        new Door(new Location[]{room3, room7}, 50);
        new Door(new Location[]{room4, room5}, 50);

        map = new Map(new ArrayList<>(List.of(room1, room2, room3, room4, room5, room6, room7)));
    }
}

// всего 2298 строк кода ;)