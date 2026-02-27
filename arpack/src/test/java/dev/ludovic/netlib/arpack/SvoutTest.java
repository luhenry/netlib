/*
 * Copyright 2020, 2021, Ludovic Henry
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Please contact git@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.arpack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SvoutTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // svout is an output method, so we just test that it runs without error

        // Note: Some native implementations may have issues with output functions
        org.junit.jupiter.api.Assumptions.assumeFalse(arpack instanceof NativeARPACK,
                "Output function not fully supported by " + arpack.getClass().getSimpleName());

        // Test basic call with standard output
        assertDoesNotThrow(() -> {
            arpack.svout(6, 10, sArray1, 4, "Test svout");
        });

        // Test with different precision
        assertDoesNotThrow(() -> {
            arpack.svout(6, 5, sArray1, 6, "High precision");
        });

        // Test with zero length (edge case)
        assertDoesNotThrow(() -> {
            arpack.svout(6, 0, sArray1, 4, "Zero length");
        });

        // Test with offset version
        assertDoesNotThrow(() -> {
            arpack.svout(6, 5, sArray1, 0, 4, "With offset");
        });
    }
}
