package de.locked.jeebase;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@WebServlet(loadOnStartup = 1)
@ApplicationPath("/api")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(MyService.class);
        return s;
    }
}
