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

public class DrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 3.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 0.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 0.0);
        blas.drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }
}
