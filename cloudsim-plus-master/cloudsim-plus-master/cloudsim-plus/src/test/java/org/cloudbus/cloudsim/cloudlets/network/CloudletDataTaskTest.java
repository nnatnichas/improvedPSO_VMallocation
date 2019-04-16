package org.cloudbus.cloudsim.cloudlets.network;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Manoel Campos da Silva Filho
 */
public class CloudletDataTaskTest {
    private static final int ID = 1;
    private CloudletSendTask instance;

    @Before
    public void setUp(){
        instance = new CloudletSendTask(ID);
    }

    @Test
    public void testSetId() {
        final int id = 90;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    @Test
    public void testSetMemory() {
        final long memory = 105L;
        instance.setMemory(memory);
        assertEquals(memory, instance.getMemory());
    }


}
