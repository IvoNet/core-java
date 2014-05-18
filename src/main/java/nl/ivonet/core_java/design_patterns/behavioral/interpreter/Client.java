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

import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Devide;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Expression;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Minus;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Multiply;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Number;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Plus;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Variable;
import nl.ivonet.core_java.helper.Tokenizer;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * The Calculator client
 * @author Ivo Woltring
 */
public class Client {

    private final Stack<Expression> values;
    private final Stack<Operator> operators;

    public Client() {
        values = new Stack<>();
        operators = new Stack<>();
    }

    public BigDecimal evaluate(final String formula) {
        return evaluate(formula, Context.NULL);
    }

    public BigDecimal evaluate(final String formula, final Context context) {

        final Tokenizer tokenizer = new Tokenizer(formula.trim());

        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();

            if ( processTokenAsVariable(token) || processTokenAsNumber(token)) {
                processTokens(context);
            } else if (!processTokenAsOperator(token)) {
                throw new IllegalStateException("A number, operator or variable should have been found");
            }
        }
        final Expression result = values.pop();
        postProsessingErrorChecks();
        return result.interpret(context);
    }

    private void postProsessingErrorChecks() {
        if (!values.isEmpty()) {
            throw new IllegalStateException("Some part of the formula is incorrect");
        }

        if (!operators.isEmpty()) {
            throw new IllegalStateException("I have some left over operators");
        }
    }

    private void processTokens(final Context context) {
        if (!operators.isEmpty()) {
            Operator operator = operators.pop();
            operator = processSignedNumber(context, operator);

            if (Operator.isPlus(operator)) {
                values.push(new Plus(values.pop(), values.pop()));
            } else if (Operator.isMinus(operator)) {
                values.push(new Minus(values.pop(), values.pop()));
            } else if (Operator.isMultiply(operator)) {
                values.push(new Multiply(values.pop(), values.pop()));
            } else if (Operator.isDevide(operator)) {
                values.push(new Devide(values.pop(), values.pop()));
            }
        }
    }

    private Operator processSignedNumber(final Context context, Operator operator) {
        if (!operators.isEmpty()) {
            values.push(new Number(String.format("%s%s", operator.toString(), values.pop()
                                                                                    .interpret(context)
                                                                                    .toPlainString())));
            operator = operators.pop();
        }
        return operator;
    }

    private boolean processTokenAsVariable(final String token) {
        if (Variable.isVariable(token)) {
            values.push(new Variable(token));
            return true;
        }
        return false;
    }


    private boolean processTokenAsOperator(final String token) {
        try {
            operators.push(Operator.fromString(token));
        } catch (final IllegalArgumentException e) {
            return false;
        }
        return true;

    }

    private boolean processTokenAsNumber(final String token) {
        try {
            this.values.push(new Number(token));
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

}
