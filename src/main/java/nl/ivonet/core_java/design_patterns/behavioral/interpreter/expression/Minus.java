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

import java.math.BigDecimal;

/**
 * Minus      := Expression - Expression
 *
 * @author Ivo Woltring
 */
public class Minus extends NonTerminalExpression {

    public Minus(final Expression right, final Expression left) {
        super(left, right);
    }

    @Override
    public BigDecimal interpret(final Context context) {
        return getLeft().interpret(context).subtract(getRight().interpret(context));
    }
}
