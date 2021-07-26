package ucf.assignments;

public class Item {
    /*
    has strings "name" and "serial"
    has double "value"
     */

    String name, serial, value;

    public Item(String name, String serial, String value) {
        /*
        using "this:
            set name
            set serial
            set value
         */
        this.name = name;
        this.serial = serial;
        this.value = value;
    }

    public String getName() {
        /*
        returns name
         */
        return name;
    }

    public void setName(String name) {
        /*
        set name
         */
        this.name = name;
    }

    public String getSerial() {
        /*
        returns serial
         */
        return serial;
    }

    public void setSerial(String serial) {
        /*
        set serial
         */
        this.serial = serial;
    }

    public String getValue() {
        /*
        returns value
         */
        return value;
    }

    public void setValue(String value) {
        /*
        set value
         */
        this.value = value;
    }
}
