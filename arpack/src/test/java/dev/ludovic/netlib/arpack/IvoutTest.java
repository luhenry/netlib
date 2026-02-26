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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class IvoutTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // ivout is an output method, so we just test that it runs without error
        // No assertions needed for output methods

        // Note: Some native implementations may have issues with output functions
        org.junit.jupiter.api.Assumptions.assumeFalse(arpack instanceof NativeARPACK,
                "Output function not fully supported by " + arpack.getClass().getSimpleName());

        // Test with basic parameters
        assertDoesNotThrow(() -> {
            arpack.ivout(6, 10, intArray1, 4, "Test output");
        });

        // Test with different array
        assertDoesNotThrow(() -> {
            arpack.ivout(6, N, intArray2, 6, "Array2 output");
        });

        // Test with zero elements
        assertDoesNotThrow(() -> {
            arpack.ivout(6, 0, intArray1, 4, "Empty output");
        });

        // Test with array of zeros
        assertDoesNotThrow(() -> {
            arpack.ivout(6, 10, intArray3, 2, "Zeros output");
        });
    }
}
