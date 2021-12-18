package lang;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Emac
 * @date 2021/12/18
 */
public class WeakReferenceList {

    private List<WeakReference<String>> elements = new LinkedList<>();

    public void add(String element) {
        elements.add(new WeakReference<>(element));
    }

    public WeakReference<String> get(int index) {
        return elements.get(index);
    }
}
