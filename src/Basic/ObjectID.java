package Basic;

import Enum.TypeEnum.ObjectType;

import java.util.Objects;

public class ObjectID {
    private final int id;
    protected String name;
    protected ObjectType type;

    public ObjectID(ObjectType type, String name) {
        this.id = Counter.next();
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Visible{" +
                "type=" + type +
                ",id=" + id +
                ",name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ObjectID newObj = (ObjectID) obj;
        return Objects.equals(this.id, newObj.id) && Objects.equals(this.type, newObj.type) && Objects.equals(this.name, newObj.name);
    }
}
