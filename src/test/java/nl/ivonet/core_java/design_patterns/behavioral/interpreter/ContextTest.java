/*
 * Copyright (c) 2014 Ivo Woltring
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.ivonet.core_java.design_patterns.behavioral.interpreter;

import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Minus;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Number;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Variable;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContextTest {


    private Context context;

    @Before
    public void setUp() throws Exception {
        context = new Context();

        context.store("theAnswer", new Number("42"));
        context.store("ten", new Number("10"));
        context.store("thirtyTwo", new Minus(new Variable("ten"), new Variable("theAnswer")));


    }

    @Test
    public void testContext() throws Exception {

        assertThat(context.getVariable("IDoNotExist"), is(Number.ZERO));

        assertThat(context.getVariable("ten")
                          .interpret(context)
                          .intValue(), is(10));

        assertThat(context.getVariable("thirtyTwo")
                          .interpret(context)
                          .intValue(), is(32));

        assertThat(context.getVariable("theAnswer")
                          .interpret(context)
                          .intValue(), is(42));

    }

}