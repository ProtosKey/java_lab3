package Simulator;

import Entity.Characters.Creature;
import Errors.DeadException;
import Enum.Status;

import java.util.ArrayList;

public class Simulator {
    private static int teak = 0;
    public ArrayList<Group> groups;

    public Simulator(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public static int getTeak() {
        return teak;
    }

    public static void next() {
        teak++;
    }

    public boolean check() {
        return this.groups.stream().mapToInt(group -> group.isAlive() ? 1 : 0).sum() > 1;
    }

    public void start() {
        while (check()) {
            int count = this.groups.size();

            System.out.println("Тик " + getTeak());

            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }

            try {
                this.groups.get(teak % count).getGroup().get(teak / count % this.groups.get(teak % count).getGroup().size()).makeTeak();
            } catch (DeadException ignored) {
            }
            groups.get(0).getGroup().get(0).setStatus(Status.DEAD);
            for (Group group : groups) {
                System.out.println(String.format("Группа \"%s\" ", group.getName()) + (group.isAlive() ? "жива" : "мертва"));
                ArrayList<Creature> toDelete = new ArrayList<>();
                for (Creature creature : group.getGroup()) {
                    if (creature.getStatus().equals(Status.DEAD)) {
                        toDelete.add(creature);
                    }
                }
                for (Creature creature : toDelete) {
                    group.getGroup().remove(creature);
                }
                System.out.println(group.getGroup().stream().map(Creature::getStatus).toList());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            next();
        }
        System.out.println("Битва окончена");
    }

    @Override
    public String toString() {
        return "Simulator{" +
                ",groupsNow=" + groups +
                '}';
    }
}
