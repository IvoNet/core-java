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

package nl.ivonet.core_java.design_patterns.behavioral.strategy;

import org.junit.Test;

/**
 * Unit tests voor de Eend simulator.
 * @author Ivo Woltring
 */
public class EendSimulatorTest {

    @Test
    public void testWildeEend() throws Exception {
        final WildeEend wildeEend = new WildeEend();
        wildeEend.toon();
        wildeEend.doeKwaak();
        wildeEend.doeVlieg();
    }

    @Test
    public void testKuifEend() throws Exception {
        final KuifEend kuifEend = new KuifEend();

        kuifEend.toon();
        kuifEend.doeKwaak();
        kuifEend.doeVlieg();

        kuifEend.raakGewond(); //gedrag verandert door omstandigheden
        kuifEend.doeVlieg();
    }

    @Test
    public void testLokEend() throws Exception {
        final LokEend lokEend = new LokEend();
        lokEend.toon();
        lokEend.doeKwaak();
        lokEend.doeVlieg();
        lokEend.setVliegGedrag(new VliegMetStraalMotor()); // geef ander gedrag
        lokEend.doeVlieg();
    }

    @Test
    public void testBadEend() throws Exception {
        final BadEend badEend = new BadEend();

        badEend.toon();
        badEend.doeKwaak();
        badEend.doeVlieg();

    }
}
