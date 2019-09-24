
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class PlayerTest {



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUse(){

        Player player = new Player();
        assertTrue(player.use("cup"));
        assertFalse(player.inList("Cup"));
        assertFalse(player.use("cUp"));
        assertFalse((player.use("pEn")));
    }

    @Test
    public void testPickUp() {

        Player player = new Player();
        assertTrue(player.pickUp("Watch"));
        assertTrue(player.inList("watch"));
        assertFalse(player.pickUp("WATch"));
        assertFalse(player.pickUp("Cup"));
    }

}
