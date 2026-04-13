package Entity.Items.Instrumets;

import Acts.ActsToCreature.Attacks.AttackWithRifle;
import Basic.Interfaces.HaveInside;
import Errors.ItemException;
import Entity.Items.Item;
import Enum.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Objects;

public class Rifle extends Weapon implements HaveInside {
    private final int maxCount;
    protected ArrayList<Item> items;
    public Rifle(int changeHP, float accuracy, int maxCount, String name) {
        super(changeHP, accuracy, name, Purpose.BULLET);
        this.maxCount = maxCount;
        this.items = new ArrayList<>();
        this.makeActCases();
    }

    public void makeActCases() {
        this.setActCases(new ArrayList<>(Collections.singleton(new AttackWithRifle(2, this))));
    }

    @Override
    public void addNeed(Item item) {
        this.items.add(item);
    }

    @Override
    public ArrayList<Item> getItems() {
        return this.items;
    }

    @Override
    public void addItem(Item item) throws ItemException {
        if (this.items.size() == this.maxCount) {
            throw new ItemException("Количество достигло максимума");
        }
        if (item.getPurpose().equals(Purpose.BULLET)) {
            this.items.add(item);
            System.out.printf("В %s добавлен %s\n", this.getName(), item.getName());
        } else throw new ItemException(String.format("Это нельзя положить в %s", this.getName()));
    }

    @Override
    public Item getItem(Item item) {
        this.items.remove(item);
        return item;
    }

    @Override
    public void setItems(ArrayList<Item> items) throws ItemException{
        if (items.size() > this.maxCount) throw new ItemException("Слишком много предметов");
        this.items = items;
    }

    @Override
    public int use() throws ItemException {
        if (this.items.size() < 1) throw new ItemException("Нет патронов");
        System.out.println("Выстрел из " + this.name);
        this.items.remove(this.items.size() - 1);
        return super.use();
    }

    @Override
    public boolean isReady() {
        return this.items.size() > 0;
    }

    @Override
    public boolean isNeedFix() {
        return this.items.size() < this.maxCount;
    }

    @Override
    public String toString() {
        return "Rifle{" + super.toString() +
                ",maxCount=" + maxCount +
                ",items=" + items +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxCount, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rifle rifle = (Rifle) o;
        return maxCount == rifle.maxCount && Objects.equals(items, rifle.items);
    }
}
