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
        this.values = new Stack<>();
        this.operators = new Stack<>();
    }

    public BigDecimal evaluate(final String formula) {
        return evaluate(formula, Context.NULL);
    }

    public BigDecimal evaluate(final String formula, final Context context) {

        final Tokenizer tokenizer = new Tokenizer(formula.trim());

        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();

            if (processTokenAsVariable(token) || processTokenAsNumber(token)) {
                processTokens(context);
            } else if (!processTokenAsOperator(token)) {
                throw new IllegalStateException("A number, operator or variable should have been found");
            }
        }
        final Expression result = this.values.pop();
        postProsessingErrorChecks();
        return result.interpret(context);
    }

    private void postProsessingErrorChecks() {
        if (!this.values.isEmpty()) {
            throw new IllegalStateException("Some part of the formula is incorrect");
        }

        if (!this.operators.isEmpty()) {
            throw new IllegalStateException("I have some left over operators");
        }
    }

    private void processTokens(final Context context) {
        if (!this.operators.isEmpty()) {
            Operator operator = this.operators.pop();
            operator = processSignedNumber(context, operator);

            if (Operator.isPlus(operator)) {
                this.values.push(new Plus(this.values.pop(), this.values.pop()));
            } else if (Operator.isMinus(operator)) {
                this.values.push(new Minus(this.values.pop(), this.values.pop()));
            } else if (Operator.isMultiply(operator)) {
                this.values.push(new Multiply(this.values.pop(), this.values.pop()));
            } else if (Operator.isDevide(operator)) {
                this.values.push(new Devide(this.values.pop(), this.values.pop()));
            }
        }
    }

    private Operator processSignedNumber(final Context context, final Operator operator) {
        if (!this.operators.isEmpty()) {
            this.values.push(new Number(String.format("%s%s", operator.toString(), this.values.pop()
                                                                                              .interpret(context)
                                                                                              .toPlainString())));
            return this.operators.pop();
        }
        return operator;
    }

    private boolean processTokenAsVariable(final String token) {
        if (Variable.isVariable(token)) {
            this.values.push(new Variable(token));
            return true;
        }
        return false;
    }


    private boolean processTokenAsOperator(final String token) {
        try {
            this.operators.push(Operator.fromString(token));
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
