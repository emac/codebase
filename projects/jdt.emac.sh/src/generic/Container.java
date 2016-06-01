package generic;

/**
 * @author Emac
 * @since 2016-05-25
 */
public class Container<T> {

    public void printClass(){
        System.out.println(getClass().getAnnotatedSuperclass());
    }
}
