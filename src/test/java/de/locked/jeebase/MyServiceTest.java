package de.locked.jeebase;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyServiceTest {
    
    @Test
    public void testGet() {
        ClientConfig dcc = new DefaultClientConfig();
        dcc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        
        Client c = Client.create(dcc);
        WebResource wr = c.resource("http://localhost/api/endpoint");
        
        MyPojo out = new MyPojo("Going to the server", 1L, new Date());
        System.out.println(out.toString());        
        MyPojo in = wr.post(MyPojo.class, out);
        System.out.println(in.toString());
    }
    
}
