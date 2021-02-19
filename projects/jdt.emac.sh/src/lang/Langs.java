package lang;

/**
 * @author Emac
 * @date 2021/2/19
 */
public class Langs {

    public static void main(String[] args) {
        OUTER: for (int i = 0; i < 10; i++)
        {
            System.out.println("Outer: " + i);
            INNER: for (int j = 0; j < 10; j++)
            {
                if ( j % 2 == 0 )
                {
                    System.out.println("Inner: " + j);
                    continue INNER;
                }
                else
                {
                    continue OUTER;
                }
            }
        }
    }
}
