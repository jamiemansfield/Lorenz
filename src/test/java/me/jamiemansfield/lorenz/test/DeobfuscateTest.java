/*
 * This file is part of Lorenz, licensed under the MIT License (MIT).
 *
 * Copyright (c) Jamie Mansfield <https://www.jamierocks.uk/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.jamiemansfield.lorenz.test;

import static org.junit.Assert.assertTrue;

import me.jamiemansfield.lorenz.MappingSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * A variety of unit tests pertaining to the de-obfuscation
 * methods in {@link MappingSet}.
 */
public final class DeobfuscateTest {

    private MappingSet mappings;

    @Before
    public void initialise() {
        this.mappings = new MappingSet();
        this.mappings.getOrCreateTopLevelClassMapping("ght")
                .setDeobfuscatedName("uk/jamierocks/Test");
    }

    @Test
    public void deobfuscateType() {
        final String type = "Lhuy;";
        assertTrue(Objects.equals(this.mappings.deobfuscateType(type), type));

        final String obfuscatedType = "Lght;";
        final String deobfuscatedType = "Luk/jamierocks/Test;";
        assertTrue(Objects.equals(this.mappings.deobfuscateType(obfuscatedType), deobfuscatedType));
    }

    @Test
    public void deobfuscateMethodSignature() {
        final String signature = "(Lhuy;)Lhuy;";
        assertTrue(Objects.equals(this.mappings.deobfuscateMethodSignature(signature), signature));

        final String simpleObfuscatedSignature = "(Lght;)Lght;";
        final String simpleDeobfuscatedSignature = "(Luk/jamierocks/Test;)Luk/jamierocks/Test;";
        assertTrue(Objects.equals(this.mappings.deobfuscateMethodSignature(simpleObfuscatedSignature), simpleDeobfuscatedSignature));

        final String primitivesObfuscatedSignature = "(Lght;Z)Lght;";
        final String primitivesDeobfuscatedSignature = "(Luk/jamierocks/Test;Z)Luk/jamierocks/Test;";
        assertTrue(Objects.equals(this.mappings.deobfuscateMethodSignature(primitivesObfuscatedSignature), primitivesDeobfuscatedSignature));
    }

}
