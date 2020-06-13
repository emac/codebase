package jdk.j8;

import org.junit.Test;

/**
 * @author Emac
 * @since 2015-11-29
 */
public class DefaultTests {

    public interface vehicle {
        default void print() {
            System.out.println("I am a vehicle!");
        }

        static void blowHorn(){
            System.out.println("Blowing horn!!!");
        }
    }

    public interface fourWheeler {
        default void print() {
            System.out.println("I am a four wheeler!");
        }
    }

    public class car implements vehicle, fourWheeler {
        public void print() {
            vehicle.super.print();
            System.out.println("I am a four wheeler car vehicle!");
        }
    }

    @Test
    public void test() {
        new car().print();
        vehicle.blowHorn();
    }

}
