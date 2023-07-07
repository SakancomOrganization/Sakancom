package objects;

import java.util.Objects;

public class Neighbor {
    private String name;
    private String description;

    public Neighbor(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Neighbor neighbor) {
            return this.name.equalsIgnoreCase(neighbor.getName())
                    && this.description.equalsIgnoreCase(neighbor.getDescription());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
