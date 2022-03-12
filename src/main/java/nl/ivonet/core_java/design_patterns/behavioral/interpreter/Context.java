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


import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Expression;
import nl.ivonet.core_java.design_patterns.behavioral.interpreter.expression.Number;

import java.util.HashMap;
import java.util.Map;

/**
 * The context = state of the interpreter.
 *
 * @author Ivo Woltring
 */
public class Context {
    public static final Context NULL = new Context();
    private final Map<String, Expression> memory;

    public Context() {
        memory = new HashMap<>();
    }

    public Expression getVariable(final String variable) {
        return memory.containsKey(variable) ? memory.get(variable) : Number.ZERO;
    }

    public void store(final String variable, final Expression expression) {
        memory.put(variable, expression);
    }


}
