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

public class DsymmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expected, dgeCcopy;

        f2j.dsymm("L", "U", M, N, 1.0, dsyA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "U", M, N, 1.0, dsyA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("L", "L", M, N, 1.0, dsyA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "L", M, N, 1.0, dsyA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "U", M, N, 1.0, dsyA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "U", M, N, 1.0, dsyA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "L", M, N, 1.0, dsyA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "L", M, N, 1.0, dsyA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("L", "U", M, N, 1.0, dsyA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "U", M, N, 1.0, dsyA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("L", "L", M, N, 1.0, dsyA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "L", M, N, 1.0, dsyA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "U", M, N, 1.0, dsyA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "U", M, N, 1.0, dsyA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "L", M, N, 1.0, dsyA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "L", M, N, 1.0, dsyA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("L", "U", M, N, 0.0, dsyA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "U", M, N, 0.0, dsyA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("L", "L", M, N, 0.0, dsyA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dsymm("L", "L", M, N, 0.0, dsyA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "U", M, N, 0.0, dsyA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "U", M, N, 0.0, dsyA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dsymm("R", "L", M, N, 0.0, dsyA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        blas.dsymm("R", "L", M, N, 0.0, dsyA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);
    }
}
