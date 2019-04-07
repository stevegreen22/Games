package gametest.enums;

/**
 * Created by SteveGreen on 06/04/2019.
 */
public enum CATEGORY {

    //Name, Identifier, Value --Todo: consider the value for later implementation as having it set maybe be too restricitve
//    EXCITED(1, 5),
//    WANT(2, 3),
//    LIKE(3, 2),
//    LOLNOPE(4, -4)

    Excited(1),
    Want(2),
    Like(3),
    Lolnope(4);

    private final int categoryId;

    CATEGORY(final int newCategoryId) {
        categoryId = newCategoryId;
    }

    public int getCategoryId() { return categoryId; }


}
