package objects;

public class HouseRate {
    private int raters;
    private double rate;

    public HouseRate() {
        raters = 0;
        rate = 0;
    }

    public int getRaters() {
        return raters;
    }

    public void setRaters(int raters) {
        this.raters = raters;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private void addNewRater() {
        raters++;
    }

    public void addNewRate(double newRateValue) {
        addNewRater();
        rate = (rate * (raters - 1) + newRateValue) / raters;
    }
}