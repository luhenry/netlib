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

import org.netlib.util.floatW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class SecondTest extends ARPACKTest {

    @ParameterizedTest
    @MethodSource("ARPACKImplementations")
    void testSanity(ARPACK arpack) {
        // Test case 1: Verify second() returns a non-negative time value
        {
            floatW t = new floatW(0.0f);
            arpack.second(t);

            // Time value should be non-negative
            assertTrue(t.val >= 0.0f, "Time value should be non-negative, got: " + t.val);
        }

        // Test case 2: Verify second() is monotonic (time increases or stays same)
        {
            floatW t1 = new floatW(0.0f);
            arpack.second(t1);

            // Small delay to ensure some time passes
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            floatW t2 = new floatW(0.0f);
            arpack.second(t2);

            // Second call should have equal or greater time
            assertTrue(t2.val >= t1.val, "Time should be monotonic: t1=" + t1.val + ", t2=" + t2.val);
        }

        // Test case 3: Multiple consecutive calls
        {
            floatW t1 = new floatW(0.0f);
            floatW t2 = new floatW(0.0f);
            floatW t3 = new floatW(0.0f);

            arpack.second(t1);
            arpack.second(t2);
            arpack.second(t3);

            // All values should be non-negative
            assertTrue(t1.val >= 0.0f, "t1 should be non-negative");
            assertTrue(t2.val >= 0.0f, "t2 should be non-negative");
            assertTrue(t3.val >= 0.0f, "t3 should be non-negative");

            // Time should be monotonic
            assertTrue(t2.val >= t1.val, "Time should be monotonic");
            assertTrue(t3.val >= t2.val, "Time should be monotonic");
        }
    }
}
