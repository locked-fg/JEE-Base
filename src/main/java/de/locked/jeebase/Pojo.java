/*
 * Copyright 2014 Dr. Franz Graf <code@Locked.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.locked.jeebase;

import java.util.Date;

public class Pojo {

    private String foo;
    private long bar;
    private Date fooBar;

    public Pojo() {
    }

    public Pojo(String foo, long bar, Date fooBar) {
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
