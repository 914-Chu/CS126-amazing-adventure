import java.util.*;

public class Computer {

    private Map<String, Integer> components = new HashMap<>();

    public Computer() {

        components.put("Motherboard", 1);
        components.put("CPU", 2);
        components.put("Cooling system", 3);
        components.put("Memory(RAM)", 4);
        components.put("PSU", 5);
        components.put("GPU", 6);
        components.put("Hard drive(SSD)", 7);
        components.put("Operating system", 8);
        components.put("Case", 9);
    }


}
