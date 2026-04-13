package Map;

import Enum.TypeEnum.ObjectType;
import Errors.LocationException;
import Map.Locations.Location;
import Basic.ObjectID;

import java.util.Arrays;
import java.util.Objects;

public class Door extends ObjectID {
    protected boolean isClosed;
    private final int minStrength;
    protected Location[] connection;

    public Door(Location[] connection, int minStrength) {
        super(ObjectType.LOCATION, "Дверь");
        this.connection = connection;
        this.minStrength = minStrength;
        this.isClosed = true;
        this.connect();
    }

    private void connect() {
        for (Location location: this.connection) {
            location.addWhereLocation(this);
        }
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void open(int strength) throws LocationException {
        if (this.minStrength > strength) throw new LocationException("Недостаточно силы для открытия");
        System.out.println(this.getName() + " открыта");
        this.isClosed = false;
    }

    public Location getWithOut(Location location) {
        if (this.connection[0] == location) return this.connection[1];
        return this.connection[0];
    }

    @Override
    public String toString() {
        return "Door{" + super.toString() +
                ",isClosed=" + isClosed +
                ",minStrength=" + minStrength +
                ",connection=" + Arrays.stream(connection).map(ObjectID::getId).toList() +
                '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), isClosed, minStrength);
        result = 31 * result + Arrays.hashCode(connection);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Door door = (Door) o;
        return isClosed == door.isClosed && minStrength == door.minStrength && Arrays.equals(connection, door.connection);
    }
}
