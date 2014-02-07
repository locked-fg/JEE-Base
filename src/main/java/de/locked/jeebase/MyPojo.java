package de.locked.jeebase;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyPojo {

    private String foo;
    private long bar;
    private Date fooBar;

    public MyPojo() {
    }

    public MyPojo(String foo, long bar, Date fooBar) {
        this.foo = foo;
        this.bar = bar;
        this.fooBar = fooBar;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public long getBar() {
        return bar;
    }

    public void setBar(long bar) {
        this.bar = bar;
    }

    public Date getFooBar() {
        return fooBar;
    }

    public void setFooBar(Date fooBar) {
        this.fooBar = fooBar;
    }

    @Override
    public String toString() {
        return "MyPojo{" + "foo=" + foo + ", bar=" + bar + ", fooBar=" + fooBar + '}';
    }
}
