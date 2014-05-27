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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for the {@link Minus} class.
 */
public class MinusTest {

    private Context contextMock;

    @Before
    public void setUp() throws Exception {
        contextMock = createMock(Context.class);
    }

    @Test
    public void testInterpret() throws Exception {
        final Expression left = new Number("52");
        final Expression right = new Number("10");

        replay(contextMock);
        assertThat(new Minus(right, left).interpret(contextMock), is(new BigDecimal(42)));
        assertThat(new Minus(left, right).interpret(contextMock), is(new BigDecimal(-42)));
        verify(contextMock);
    }
}