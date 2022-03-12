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

import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Number;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link Client} class.
 */
public class ClientTest {


    private Context context;
    private Client client;

    @Before
    public void setUp() throws Exception {
        this.context = new Context();
        this.client = new Client();
    }

    @Test
    public void testPlus() throws Exception {
        assertEquals(new BigDecimal("10"), this.client.evaluate("5+5"));
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(new BigDecimal("10"), this.client.evaluate("15-5"));
        assertEquals(new BigDecimal("10"), this.client.evaluate("15 -    5"));
        assertEquals(new BigDecimal("10"), this.client.evaluate("15 - 5   "));
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(new BigDecimal("42"), this.client.evaluate("7*6"));
        assertEquals(new BigDecimal("42"), this.client.evaluate("7 * 6"));
        assertEquals(new BigDecimal("42"), this.client.evaluate("7* 6 "));
    }

    @Test
    public void testDevide() throws Exception {
        assertEquals(new BigDecimal("42"), this.client.evaluate("420:10"));
        assertEquals(new BigDecimal("42"), this.client.evaluate("420 :10"));
    }

    @Test
    public void testCompountExpresson() throws Exception {
        assertEquals(new BigDecimal("42"), this.client.evaluate("6*7+10-42:10*42"));
        assertEquals(new BigDecimal("42"), this.client.evaluate("6* 7+ 10 -42 :10*42"));
        assertEquals(new BigDecimal("42"), this.client.evaluate(" 6* 7+ 10 -42:10 * 42  "));

    }

    @Test
    public void testEvaluateVariable() throws Exception {

        this.context.store("a", new nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Number("10"));
        this.context.store("b", new Number("1.3333"));

        assertEquals(new BigDecimal("101.3333"), this.client.evaluate("a*a+b", this.context));
    }

    @Test
    public void testNegativeStartingNumber() throws Exception {
        assertEquals(new BigDecimal("-42"), this.client.evaluate("-42"));
        assertEquals(BigDecimal.ZERO, this.client.evaluate("-0"));
    }

    @Test
    public void testPositiveStartingNumber() throws Exception {
        assertEquals(new BigDecimal("+42"), this.client.evaluate("+42"));
        assertEquals(BigDecimal.ZERO, this.client.evaluate("+0"));
    }

    @Test
    public void testVariableWithNoContextReference() throws Exception {
        assertEquals(BigDecimal.ZERO, this.client.evaluate(
              "noReferenceAvailableShouldReturnNullBecauseItIsDesignedThatWay"));
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongVariable() throws Exception {
        this.client.evaluate("AWrongVariableBecauseItStartsWithACapitalCasedLetter");

    }
}
