package objects;

public class Neighbor {
    private String name;
    private String description;

    public Neighbor() {

    }
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
            return this.name.equals(neighbor.getName())
                    && this.description.equals(neighbor.getDescription());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
