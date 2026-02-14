# ARPACK Comprehensive Test Coverage Plan

## Overview
Add comprehensive test implementations for all 58 stubbed ARPACK test classes, following the pattern established in the BLAS tests (specifically [DaxpyTest.java](../blas/src/test/java/dev/ludovic/netlib/blas/DaxpyTest.java)).

## Current State
- **Total test files**: 59 (including ARPACKTest.java base class)
- **Stubbed tests**: 58 (all contain `assumeTrue(false)`)
- **Base test class**: [ARPACKTest.java](../arpack/src/test/java/dev/ludovic/netlib/arpack/ARPACKTest.java) - already properly configured
- **Reference implementation**: F2jARPACK (f2j) available for comparison

## Test Implementation Strategy

### Pattern to Follow (from DaxpyTest.java)
```java
@ParameterizedTest
@MethodSource("ARPACKImplementations")
void testSanity(ARPACK arpack) {
    // 1. Declare expected and test array variables
    // 2. Call f2j.method(...) with cloned inputs -> store in expected
    // 3. Call arpack.method(...) with cloned inputs -> store in test copy
    // 4. Assert arrays/values are equal within epsilon tolerance
    // 5. Repeat for multiple parameter combinations
}
```

### Test Categories (58 test files)

#### 1. Utility Functions (11 tests)
Simple array/output operations:
- **Integer utilities**: IcopyTest, IsetTest, IswapTest, IcnteqTest, IvoutTest
- **Double output**: DvoutTest, DmoutTest
- **Float output**: SvoutTest, SmoutTest
- **Statistics**: DstatnTest, SstatnTest, DstatsTest, SstatsTest

**Approach**: Test with various array sizes and values

#### 2. Sorting Functions (4 tests)
Array sorting/comparison operations:
- DsortrTest, DsortcTest
- SsortrTest, SsortcTest

**Approach**: Test with unsorted arrays, verify correct ordering

#### 3. Core Eigenvalue Operations (43 tests)
Complex iterative eigenvalue solver routines:

**Double precision symmetric** (9 tests):
- DsaitrTest, DsappsTest, Dsaup2Test, DsaupdTest
- DsconvTest, DseigtTest, DsesrtTest, DseupdTest, DsgetsTest

**Float precision symmetric** (9 tests):
- SsaitrTest, SsappsTest, Ssaup2Test, SsaupdTest
- SsconvTest, SseigtTest, SsesrtTest, SseupdTest, SsgetsTest

**Double precision non-symmetric** (9 tests):
- DnaitrTest, DnappsTest, Dnaup2Test, DnaupdTest
- DnconvTest, DneighTest, DneupdTest, DngetsTest, Dgetv0Test

**Float precision non-symmetric** (9 tests):
- SnaitrTest, SnappsTest, Snaup2Test, SnaupdTest
- SnconvTest, SneighTest, SneupdTest, SngetsTest, Sgetv0Test

**Specialized operations** (7 tests):
- DlaqrbTest, SlaqrbTest (QR eigenvalue)
- DstqrbTest, SstqrbTest (tridiagonal QR)
- SecondTest (timing utility)

**Approach**: These require complex setup with matrices, eigenvector workspace, and multiple parameters. Will need to:
- Create appropriate test matrices
- Initialize required workspace arrays
- May need iterative IDO patterns for some methods
- Some methods modify org.netlib.util.*W wrapper objects

## Implementation Steps

### Step 1: Analyze Method Signatures
- Read ARPACK.java interface to understand all method signatures
- Identify parameter types and return patterns
- Document methods that use org.netlib.util.*W wrappers (intW, doubleW, floatW)

### Step 2: Enhance ARPACKTest Base Class
Similar to BLASTest enhancements:
- Add test data arrays (matrices, vectors) as protected fields
- Add utility methods for:
  - Creating test matrices (symmetric, non-symmetric, tridiagonal)
  - Array cloning helpers
  - Assertion helpers for wrapped values (intW, doubleW, floatW)
- Add appropriate epsilon constants if needed

### Step 3: Implement Simple Tests First (15 tests)
Start with straightforward utility and sorting functions:
- **Phase 3a**: Integer utilities (5 tests) - icopy, iset, iswap, icnteq, ivout
- **Phase 3b**: Output functions (4 tests) - dvout, dmout, svout, smout
- **Phase 3c**: Sorting functions (4 tests) - dsortr, dsortc, ssortr, ssortc
- **Phase 3d**: Statistics (2 tests) - dstatn, sstatn, dstats, sstats

### Step 4: Implement Intermediate Tests (7 tests)
Specialized operations with moderate complexity:
- DstqrbTest, SstqrbTest (tridiagonal QR)
- DlaqrbTest, SlaqrbTest (QR eigenvalue)
- SecondTest (timing)
- Dgetv0Test, Sgetv0Test (initial vector generation)

### Step 5: Implement Core Eigenvalue Tests (36 tests)
Most complex category - implement in matched pairs (double/float):
- **Phase 5a**: Symmetric operations (18 tests)
  - *aitr routines (2): iteration
  - *apps routines (2): application
  - *aup2 routines (2): update level 2
  - *aupd routines (2): main driver
  - *conv routines (2): convergence check
  - *eigt routines (2): eigenvalue computation
  - *esrt routines (2): sort
  - *eupd routines (2): extract results
  - *gets routines (2): get shifts

- **Phase 5b**: Non-symmetric operations (16 tests)
  - Similar structure to symmetric but for non-symmetric problems
  - *aitr, *apps, *aup2, *aupd, *conv, *eigh, *eupd, *gets

### Step 6: Run Tests and Fix Issues
After each phase:
- Run: `mvn clean test -pl arpack`
- Fix any failures
- Adjust epsilon tolerances if needed
- Document any methods that cannot be easily tested

### Step 7: Verify Complete Coverage
- Ensure all 58 test files have real implementations
- No remaining `assumeTrue(false)` statements
- All tests pass: `mvn clean test -pl arpack`

## Expected Challenges

1. **Complex method signatures**: Many ARPACK methods have 10+ parameters
2. **Wrapper objects**: Methods using org.netlib.util.*W require special handling
3. **Iterative methods**: Some methods (like *aupd) use reverse communication (IDO pattern)
4. **State dependencies**: Some methods require specific initialization sequences
5. **Numerical precision**: May need to adjust epsilon values for different operations

## Success Criteria

- ✅ All 58 stubbed tests have real implementations
- ✅ No `assumeTrue(false)` statements remain
- ✅ All tests compare against f2j reference implementation
- ✅ Command `mvn clean test -pl arpack` succeeds
- ✅ ARPACKTest base class has appropriate test utilities
- ✅ Tests follow same pattern as BLAS tests

## Estimated Scope

- **Simple tests (15)**: ~5-10 lines each = 75-150 lines
- **Intermediate tests (7)**: ~15-25 lines each = 105-175 lines
- **Complex tests (36)**: ~20-40 lines each = 720-1440 lines
- **Base class enhancements**: ~100-200 lines
- **Total new code**: ~1000-2000 lines

This is a comprehensive refactoring that will significantly improve test coverage for the ARPACK module.
