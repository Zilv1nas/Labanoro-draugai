package lt.virai.labanoroDraugai.ui.rest;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Žilvinas on 2016-04-12.
 */
public class AuthenticationControllerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void login() throws Exception {
        System.out.println(MacProvider.generateKey());
    }

}