public class Item {
    /*
    has strings "name" and "serial"
    has double "value"
     */

    String name, serial;
    Double value;

    public Item(String name, String serial, Double value) {
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

    public Double getValue() {
        /*
        returns value
         */
        return value;
    }

    public void setValue(Double value) {
        /*
        set value
         */
        this.value = value;
    }
}
