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

package nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression;

import nl.ivonet.core_java.design_patterns.behavioral.interpreter.Context;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class VariableTest {

    private Context contextMock;

    @Before
    public void setUp() throws Exception {
        contextMock = createMock(Context.class);
    }

    @Test
    public void testInterpret() throws Exception {
        expect(contextMock.getVariable("a")).andReturn(new Number("42"));

        final Expression expression = new Variable("a");
        replay(contextMock);
        final BigDecimal interpret = expression.interpret(contextMock);
        verify(contextMock);
        assertEquals(42, interpret.intValue());


    }

    @Test
    public void testIsVariable() throws Exception {
        assertFalse(Variable.isVariable("1"));
        assertFalse(Variable.isVariable("A"));
        assertFalse(Variable.isVariable(""));
        assertTrue(Variable.isVariable("a"));
        assertTrue(Variable.isVariable("a1"));
        assertTrue(Variable.isVariable("a10"));
    }


}
