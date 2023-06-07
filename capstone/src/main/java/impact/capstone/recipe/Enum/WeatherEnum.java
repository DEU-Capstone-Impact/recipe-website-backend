package impact.capstone.recipe.Enum;

public enum WeatherEnum {
    SUNNY(1),
    CLOUDY(2),
    RAIN(3),
    SNOW(4);

    private final int value;

    WeatherEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}