import org.junit.jupiter.api.Test;
import ucf.assignments.Item;
import ucf.assignments.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class tests {
    @Test
    private void testAdd() {
        /*
        create two list items: expected and actual
        using addItem, add item to actual
        add item to expected using invList.add
        call assertEquals
         */
        List expected = new List();
        List actual = new List();

        actual.addItem();
        expected.invList.add(new Item("name", "XXXXXXXXXX", "00.00"));

        assertEquals(expected.invList, actual.invList);
    }

    @Test
    private void testDelete() {
        /*
        create two list items: expected and actual
        add two items to actual
        add one item to expected
        delete one item from actual
        call assertEquals
         */
        List expected = new List();
        List actual = new List();

        actual.invList.add(new Item("name", "XXXXXXXXXX", "00.00"));
        Item it = new Item("name", "XXXXXXXXXX", "00.00");
        actual.invList.add(it);

        expected.invList.add(new Item("name", "XXXXXXXXXX", "00.00"));

        actual.deleteItem(it);

        assertEquals(expected.invList, actual.invList);
    }

    @Test
    private void testSearch() {
        /*
        create 3 list items: expected, actual, and list
        add two items to list with names "car" and "ball"
        set actual's invlist to list.search("car")
        add one item to expected's invList with the name "car"
        call assertEquals
         */
        List list = new List();
        List actual = new List();
        List expected = new List();

        list.invList.add(new Item("car", "XXXXXXXXXX", "00.00"));
        list.invList.add(new Item("ball", "XXXXXXXXXX", "00.00"));
        actual.invList = list.search("car");

        expected.invList.add(new Item("car", "XXXXXXXXXX", "00.00"));

        assertEquals(expected.invList, actual.invList);

    }
}
