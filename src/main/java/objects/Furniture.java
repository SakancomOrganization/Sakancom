package objects;

import java.util.Objects;

public class Furniture {
    private String description;
    private String image;

    public Furniture(String description, String image) {
        this.description = description;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Furniture furniture) {
            return this.description.equals(furniture.getDescription())
                    && this.image.equals(furniture.getImage());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, image);
    }
}
