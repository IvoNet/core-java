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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorTest {

    @Test
    public void testFromString() throws Exception {
        assertEquals(Operator.PLUS, Operator.fromString("+"));
        assertEquals(Operator.MINUS, Operator.fromString("-"));
        assertEquals(Operator.MULTIPLY, Operator.fromString("*"));
        assertEquals(Operator.DEVIDE, Operator.fromString(":"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrong() throws Exception {
        Operator.fromString("W");
    }
}