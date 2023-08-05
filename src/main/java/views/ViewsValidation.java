package views;

public class ViewsValidation {
    private ViewsValidation() {

    }

    public static boolean isValidUserField(String field) {
        return field.equalsIgnoreCase("firstName")
                || field.equalsIgnoreCase("secondName")
                || field.equalsIgnoreCase("lastName")
                || field.equalsIgnoreCase("city")
                || field.equalsIgnoreCase("street")
                || field.equalsIgnoreCase("building")
                || field.equalsIgnoreCase("floor")
                || field.equalsIgnoreCase("email")
                || field.equalsIgnoreCase("phoneNumber")
                || field.equalsIgnoreCase("birthDate")
                || field.equalsIgnoreCase("major");
    }

    public static boolean isValidBuildingField(String field) {
        return field.equalsIgnoreCase("name")
                || field.equalsIgnoreCase("city")
                || field.equalsIgnoreCase("street");
    }

    public static boolean isValidHouseField(String field) {
        return field.equalsIgnoreCase("monthlyRent")
                || field.equalsIgnoreCase("includesElectricity")
                || field.equalsIgnoreCase("includesWater")
                || field.equalsIgnoreCase("hasInternet")
                || field.equalsIgnoreCase("hasTelephone")
                || field.equalsIgnoreCase("hasBalcony")
                || field.equalsIgnoreCase("bedroomsNum")
                || field.equalsIgnoreCase("bathroomsNum")
                || field.equalsIgnoreCase("houseClassificationByGender");
    }

    public static boolean isNegativeNumber(int roomsNum) {
        return roomsNum < 0;
    }

    public static boolean isValidRate(int rate) {
        return rate >=0 && rate <= 5;
    }
}
