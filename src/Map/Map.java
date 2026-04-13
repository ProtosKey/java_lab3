package Map;

import Map.Locations.Location;

import java.util.ArrayList;
import java.util.Objects;

public record Map(ArrayList<Location> locations) {
    public Location getLocation(int index) {
        return this.locations.get(index);
    }

    @Override
    public String toString() {
        return "Map{" +
                "locations=" + locations +
                '}';
    }

    @Override
    public int hashCode() {
        return this.locations.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Map newObj = (Map) obj;
        return Objects.equals(this.locations, newObj.locations);
    }
}
