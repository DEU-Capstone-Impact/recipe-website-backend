package impact.capstone.recipe.Enum;

public enum CategoryEnum {
    KOREAN(1),
    CHINESE(2),
    JAPANESE(3),
    WESTERN(4);

    private final int value;

    CategoryEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
