
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
        assertTrue(player.leave("cup"));
        assertFalse(player.leave("cUp"));
        assertFalse((player.leave("pEn")));
    }

    @Test
    public void testPickUp() {

        Player player = new Player();
        assertTrue(player.pickUp("Watch"));
        assertFalse(player.pickUp("WATch"));
        assertFalse(player.pickUp("Cup"));
    }

}
