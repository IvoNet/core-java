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

/**
 *
 * @author Ivo Woltring
 */
public enum Operator {
    PLUS("+"),
    MINUS("-"),
    DEVIDE(":"),
    MULTIPLY("*");

    private final String operator;

    Operator(final String s) {
        this.operator = s;
    }


    public static Operator fromString(final String input) {
        for (final Operator value : values()) {
            if (value.operator.equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format("Operator [%s] is not recognized as an operator.", input));
    }

    public static boolean isDevide(final Operator operator) {
        return operator == DEVIDE;
    }

    public static boolean isMultiply(final Operator operator) {
        return operator == MULTIPLY;
    }

    public static boolean isMinus(final Operator operator) {
        return operator == MINUS;
    }

    public static boolean isPlus(final Operator operator) {
        return operator == PLUS;
    }


    @Override
    public String toString() {
        return operator;
    }
}
