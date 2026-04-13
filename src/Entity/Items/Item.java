package Entity.Items;

import Enum.TypeEnum.ObjectType;
import Basic.ObjectID;
import Enum.*;

import java.util.Objects;

public abstract class Item extends ObjectID {
    protected Purpose purpose;
    public Item(Purpose purpose, String name) {
        super(ObjectType.ITEM, name);
        this.purpose = purpose;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    @Override
    public String toString() {
        return "Item{" + super.toString() +
                ",purpose=" + purpose +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purpose);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return purpose == item.purpose;
    }
}
