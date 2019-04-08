package gametest.enums;

/**
 * Created by SteveGreen on 07/04/2019.
 */
public enum ACTIVE {

    Active(0),
    Inactive(1);

    private final int stateIdentifier;

    ACTIVE(final int newStateIdentifier) {
        stateIdentifier = newStateIdentifier;
    }

    public int getStateIdentifier() { return stateIdentifier; }

}
