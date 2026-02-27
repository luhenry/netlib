# Comprehensive Test Coverage Plan — BLAS, LAPACK, ARPACK

## Overview

This document describes all work done to implement comprehensive test coverage across all three modules of the netlib project: BLAS, LAPACK, and ARPACK. The work replaced `assumeTrue(false)` stubs with functional tests comparing each implementation against the F2jLAPACK/F2jBLAS/F2jARPACK reference implementations.

## Project Statistics

| Module | Test Files | Implemented | Stubs Remaining | Tests | Skipped |
|--------|-----------|-------------|-----------------|-------|---------|
| BLAS   | 67        | 67 (100%)   | 0               | ~335  | ~0      |
| ARPACK | 58        | 58 (100%)   | 0               | ~290  | ~12     |
| LAPACK | 723       | 672 (93%)   | 51              | ~4300 | ~450    |
| **Total** | **848** | **797 (94%)** | **51**       | **~4925** | **~462** |

- **Test Framework**: JUnit 5 with parameterized tests
- **Reference Implementations**: F2jBLAS, F2jLAPACK (LAPACK 3.1), F2jARPACK
- **Native Implementation**: Reference LAPACK 3.12 / ARPACK-NG (via JNI)
- **Test Command**: `mvn clean test` (clean is mandatory — Maven doesn't reliably detect native changes)
- **CI**: All 20/20 jobs pass (Linux + macOS, x64 + arm64, JDK 8/11/17/21/23)

## Critical Bug Fixes

### 1. Fortran Hidden String Length Arguments (JNI)

**Problem**: The JNI code generator (`generator.py`) was not passing Fortran's hidden string length arguments for `CHARACTER` parameters, causing `lsamen` to always return false.

**Fix**: Modified `generator.py` to detect `JString`/`JStringW` parameters, append `int len_{name}` to function pointer declarations, and use `GetStringUTFLength` at call time. Regenerated all JNI bindings.

### 2. JNI Symbol Resolution (RTLD_LOCAL)

**Problem**: Native library loading used `RTLD_GLOBAL`, causing symbol conflicts between different LAPACK/BLAS providers.

**Fix**: Changed to `RTLD_LOCAL` with explicit library handles for JNI symbol resolution.

### 3. StringW JNI Bug

**Problem**: `StringW` output parameters (e.g., EQUED in equilibration routines) not propagated back through JNI.

**Status**: Documented; native implementations compute correct results but the character output doesn't get written back.

## Key Lessons Learned

### LAPACK 3.12 vs F2j (LAPACK 3.1) Differences

| Category | Example | Solution |
|----------|---------|----------|
| Iteration counts | dlasq2/slasq2 | Compare eigenvalues only, skip diagnostics |
| Eigenvalue ordering | dgegs/dgegv | Sort before comparison |
| Tolerance issues | dgegv beta values | Use absolute tolerance |
| Sign ambiguities | dgghrd Q/Z matrices | Compare invariants or skip |
| Blocking strategies | sggrqf | Skip when intermediate values incomparable |
| F2j bugs | dlasd1 array bounds | Skip and document |
| Algorithm changes | dlahr2 vs dlahrd | Skip native, test f2j/java only |
| Column pivoting | dgeqpf/dgeqp3 | Skip — pivot ordering differs |

### D&C Internal Routine Testing Strategy

Complex internal Divide-and-Conquer routines (dlaed2/3/8, dlals0/dlalsa) require carefully constructed intermediate state. Rather than manually constructing this state, these are tested **indirectly** through their parent routines:
- **dlaed2/dlaed3**: Tested via `dlaed1` with appropriate n and cutpnt
- **dlaed8**: Tested via `dlaed0` with ICOMPQ=1
- **dlals0/dlalsa**: Tested via `dlalsd` with smlsiz=3
- **dlaed0/dlaed7/dlaed9/dlaeda/dlaed6**: Tested directly

### INDXQ Local vs Global Indexing

For D&C merge routines, the INDXQ permutation array requires **local 1-based indices** in the second half. Internal routines (dlaed2, dlasd2) add an offset (NLP1 or N1) to convert local→global.

### Tolerance Patterns

- **LAPACK**: `Math.scalb(epsilon, Math.getExponent(getMaxValue(expected)))` — scales tolerance relative to value magnitude
- **ARPACK**: `Math.max(epsilon, epsilon * maxAbs(expected))` — similar relative tolerance
- **Float epsilon**: `1e-5f` (sepsilon), **Double epsilon**: `1e-14d` (depsilon)
- Eigenvalue routines may need relaxed tolerance (1e-13) due to version differences

## Module Details

### BLAS (67 test files, 100% implemented)

All 67 BLAS test files have functional implementations. Tests cover Level 1 (vector), Level 2 (matrix-vector), and Level 3 (matrix-matrix) operations in both single and double precision. Full Java BLAS implementations were also added.

### ARPACK (58 test files, 100% implemented)

All 58 ARPACK test files have functional implementations covering:

- **Utility functions** (11): icopy, iset, iswap, icnteq, ivout, dvout, dmout, svout, smout, dstatn/sstatn
- **Sorting functions** (4): dsortr, dsortc, ssortr, ssortc
- **Symmetric eigenvalue** (18): dsaitr/ssaitr, dsapps/ssapps, dsaup2/ssaup2, dsaupd/ssaupd, dsconv/ssconv, dseigt/sseigt, dsesrt/ssesrt, dseupd/sseupd, dsgets/ssgets
- **Non-symmetric eigenvalue** (18): dnaitr/snaitr, dnapps/snapps, dnaup2/snaup2, dnaitr/snaitr, dnconv/snconv, dneigh/sneigh, dneupd/sneupd, dngets/sngets, dgetv0/sgetv0
- **Specialized** (7): dlaqrb/slaqrb, dstqrb/sstqrb, second

The DstqrbTest uses relative tolerance `Math.max(depsilon, depsilon * maxAbs(expected_d))` to handle cross-platform precision differences (macOS Intel vs ARM).

### LAPACK (723 test files, 672 implemented, 51 stubs)

#### Implemented by Category (672 files)

**Tier 1: Utilities & Machine Constants** — ddisna, sdisna, dlarnv, slarnv, dlaruv, slaruv

**Tier 2-3: Elementary Math, Matrix Copy/Scale/Set** — all implemented

**Tier 4: Matrix Norms & Condition Numbers** — dppequ, sppequ, dgbcon, sgbcon, dlanhs, slanhs, dlantb, slantb

**Tier 5: Householder & Givens** — all implemented

**Tier 6: Basic Factorizations** — dgetf2, sgetf2, dpotf2, spotf2, dgbtf2, sgbtf2, etc.

**Tier 7: Triangular Operations** — dgbtrs, sgbtrs, dgtts2, sgtts2, dptts2, sptts2, etc.

**Tier 8: High-Level Drivers** — 37 files

**Tier 9: Orthogonal Matrix Operations** — 48 files

**Tier 10: Reduction to Standard Forms** — dlabrd, slabrd, dlatrd, slatrd, etc.

**Tier 11: Least Squares** — 14 files

**Tier 12-13: SVD** — dlasq2, slasq2, dgesvd, sgesvd, dgesdd, sgesdd

**Tier 14-15: Symmetric Eigenvalue** — Full coverage including banded (dsbev*, ssbev*), packed (dspev*, sspev*), generalized (dsbg*, dspg*)

**Tier 16: Non-Symmetric Eigenvalue** — dgegs, sgegs, dgegv, sgegv, dggev, sggev, dggevx, sggevx, dhgeqz, shgeqz, dtrevc, strevc, dtrexc, strexc, dtrsen, strsen, dtrsna, strsna, dtrsyl, strsyl

**Tier 17: D&C Support (28 newly enabled)** — This session enabled 28 previously-skipped D&C tests:
- **Direct tests**: dlaed0, slaed0, dlaed1, slaed1, dlaed6, slaed6, dlaed9, slaed9, dlalsd, slalsd, dlahr2, slahr2, dlahrd, slahrd
- **Indirect tests** (via parent routines): dlaed2/slaed2 (via dlaed1), dlaed3/slaed3 (via dlaed1), dlaed7/slaed7 (via dlaed0), dlaed8/slaed8 (via dlaed0), dlaeda/slaeda (via dlaed0), dlals0/slals0 (via dlalsd), dlalsa/slalsa (via dlalsd)
- dlahr2/slahr2 and dlahrd/slahrd skip NativeLAPACK/JNILAPACK due to algorithm changes between LAPACK 3.1 and 3.2+

**Other**: Symmetric indefinite (dsytrf, dsytrs, dsytri, dsycon, dsyrfs, dsptrf, dsptrs, etc.), balancing (dgebal, dgebak, dggbal, dggbak), tridiagonal utilities (dlagtf, dlagts, dlagtm), Householder/RZ (dlarz, dlarzb, dlatrz, dlatzm), equilibration (dlaqge, dlaqsy, dlaqgb, etc.), generalized Sylvester (dtgsyl, dtgsy2, dtgsna, dtgsja), precision conversion (dlag2s, slag2d), and many more.

#### Remaining Stubs (51 files, all with explanatory skip comments)

| Test | Reason |
|------|--------|
| dgees/sgees, dgeesx/sgeesx | SELECT callback NPE in JNI dispatch |
| dgges/sgges, dggesx/sggesx | SELCTG callback NPE in JNI dispatch |
| dgghrd/sgghrd, dtgex2/stgex2 | Sign ambiguity in orthogonal transformations |
| dgeqpf/sgeqpf, dgeqp3/sgeqp3, dlaqp2/slaqp2, dlaqps/slaqps | Column pivot ordering differs between LAPACK versions |
| dgetc2/sgetc2, dgesc2/sgesc2, dlatdf/slatdf | Complete pivoting differs between versions |
| sggrqf, sggsvp | Blocking/rank differences |
| dlasd1/slasd1 | F2j array indexing bug |
| dlasda/slasda | Internal to dbdsdc, tested indirectly |
| dlarrb/slarrb, dlarrf/slarrf, dlarrv/slarrv | MRRR internals, tested via dstemr |
| dlaebz/slaebz | Internal bisection, tested via dstebz |
| dlaqr0-5/slaqr0-5 | QR algorithm internals, tested via dhseqr |
| dlazq3/slazq3, dlazq4/slazq4 | dqds internals, tested via dlasq2 |

### Remaining Opportunities

1. **Fix JNI callback handling** — Would enable 8 files (dgees/sgees/dgeesx/sgeesx/dgges/sgges/dggesx/sggesx)
2. **Fix F2j bugs** — Would enable dlasd1/slasd1
3. **Version-tolerant testing** — Could test column pivoting routines by comparing invariants instead of pivot order
4. **StringW JNI fix** — Would enable proper verification of EQUED output in equilibration routines
