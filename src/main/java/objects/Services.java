package objects;

public class Services {
    private boolean includesElectricity;
    private boolean includesWater;
    private boolean hasInternet;
    private boolean hasTelephone;
    private boolean hasBalcony;
    private int bedroomsNum;
    private int bathroomsNum;

    public Services(boolean includesElectricity, boolean includesWater, boolean hasInternet, boolean hasTelephone, boolean hasBalcony, int bedroomsNum, int bathroomsNum) {
        this.includesElectricity = includesElectricity;
        this.includesWater = includesWater;
        this.hasInternet = hasInternet;
        this.hasTelephone = hasTelephone;
        this.hasBalcony = hasBalcony;
        this.bedroomsNum = bedroomsNum;
        this.bathroomsNum = bathroomsNum;
    }

    public boolean isIncludesElectricity() {
        return includesElectricity;
    }

    public void setIncludesElectricity(boolean includesElectricity) {
        this.includesElectricity = includesElectricity;
    }

    public boolean isIncludesWater() {
        return includesWater;
    }

    public void setIncludesWater(boolean includesWater) {
        this.includesWater = includesWater;
    }

    public boolean isHasInternet() {
        return hasInternet;
    }

    public void setHasInternet(boolean hasInternet) {
        this.hasInternet = hasInternet;
    }

    public boolean isHasTelephone() {
        return hasTelephone;
    }

    public void setHasTelephone(boolean hasTelephone) {
        this.hasTelephone = hasTelephone;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public int getBedroomsNum() {
        return bedroomsNum;
    }

    public void setBedroomsNum(int bedroomsNum) {
        this.bedroomsNum = bedroomsNum;
    }

    public int getBathroomsNum() {
        return bathroomsNum;
    }

    public void setBathroomsNum(int bathroomsNum) {
        this.bathroomsNum = bathroomsNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Services services) {
            return this.includesElectricity == services.isIncludesElectricity()
                    && this.includesWater == services.isIncludesWater()
                    && this.hasInternet == services.isHasInternet()
                    && this.hasTelephone == services.isHasTelephone()
                    && this.hasBalcony == services.isHasBalcony()
                    && this.bedroomsNum >= services.getBedroomsNum()
                    && this.bathroomsNum >= services.getBathroomsNum();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
