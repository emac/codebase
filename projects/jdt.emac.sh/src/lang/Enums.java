package lang;

/**
 * @author Emac
 * @date 2021/2/19
 */
public class Enums {

    public static void main(String[] args) {
        MyEnum enm = null;
        switch (enm)
        {
            case A:
            {
                System.out.println(MyEnum.A);
                break;
            }
            case B:
            {
                System.out.println(MyEnum.B);
                break;
            }
            default:
        }
    }

    private enum MyEnum {
        A, B;
    }
}
