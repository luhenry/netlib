/*
* Copyright 2020, Ludovic Henry
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
*/

import dev.ludovic.netlib.BLAS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class Dsyr2Test extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int n = 4;
        double alpha = 0.1;
        double[] x = new double[] {
            1.0, 2.0, 2.1,  4.0 };
        double[] y = new double[] {
            3.0, 4.0, 5.1, -1.0 };
        double[] a = new double[] {
            1.0, 2.0, 3.0, 4.0,
            2.0, 2.0, 3.0, 4.0,
            3.0, 3.0, 3.0, 4.0,
            4.0, 4.0, 4.0, 4.0 };
        double[] expectedU = new double[] {
             1.6,  2.0,   3.0, 4.0,
             3.0,  3.6,   3.0, 4.0,
            4.14, 4.86, 5.142, 4.0,
             5.1,  5.4,  5.83, 3.2 };
        double[] expectedL = new double[] {
             1.6,  3.0,  4.14, 5.1,
             2.0,  3.6,  4.86, 5.4,
             3.0,  3.0, 5.142, 5.83,
             4.0,  4.0,   4.0, 3.2 };

        double[] a1 = a.clone();
        blas.dsyr2("U", n, alpha, x, 1, y, 1, a1, n);
        assertArrayEquals(expectedU, a1, depsilon);

        double[] a2 = a.clone();
        blas.dsyr2("L", n, alpha, x, 1, y, 1, a2, n);
        assertArrayEquals(expectedL, a2, depsilon);
    }
}
