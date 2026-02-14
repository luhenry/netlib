# LAPACK Test Implementation Plan

## Overview
Implement comprehensive test coverage for 724 LAPACK test files, replacing all `assumeTrue(false)` stubs with functional tests that compare each implementation against the F2jLAPACK reference implementation.

## Project Statistics
- **Total Test Files**: 724 (362 double precision + 362 single precision)
- **Test Framework**: JUnit 5 with parameterized tests
- **Reference Implementation**: F2jLAPACK (Fortran-to-Java)
- **Test Command**: `mvn clean test -pl lapack`
- **Implementations Tested**: LAPACK, NativeLAPACK, JavaLAPACK, F2jLAPACK, JNILAPACK

## Implementation Strategy

### Phase-Based Approach
Given the large number of files (724 vs 58 for ARPACK), we'll use a **tiered implementation strategy**:
1. Start with simple utility functions to build confidence and test patterns
2. Progress to foundational algorithms (factorizations)
3. Move to higher-level solvers (eigenvalues, SVD)
4. Handle complex support routines last

### Testing Pattern
Following the proven ARPACK approach:
```java
@ParameterizedTest
@MethodSource("LAPACKImplementations")
void testSanity(LAPACK lapack) {
    // 1. Call f2j reference implementation with test data
    double[] expected = ...;
    f2j.routine(params, expected, ...);

    // 2. Call test implementation with same test data
    double[] actual = ...;
    lapack.routine(params, actual, ...);

    // 3. Compare outputs using appropriate epsilon
    assertArrayEquals(expected, actual, depsilon);
}
```

## Categorization by Complexity and Dependencies

### **TIER 1: Utilities & Machine Constants** (25 files)
**Complexity**: Very Simple
**Dependencies**: None
**Estimated Time**: 1-2 hours

Files:
- **Ilaenv** - Environment tuning parameters (trivial return values)
- **Lsame** - Case-insensitive character comparison
- **Machine Constants** (12 files):
  - Dlamc1, Dlamc2, Dlamc3, Dlamc4, Dlamc5, Dlamch (double precision)
  - Slamc1, Slamc2, Slamc3, Slamc4, Slamc5, Slamch (single precision)
- **Logical Checks** (12 files):
  - Disnan, Dlaisnan (check NaN for double)
  - Sisnan, Slaisnan (check NaN for single)

**Implementation Notes**:
- Most return constants based on IEEE 754 floating-point
- Ilaenv returns optimal block sizes (can use simple defaults)
- Perfect starting point to establish test patterns

### **TIER 2: Elementary Math Functions** (26 files)
**Complexity**: Simple
**Dependencies**: TIER 1
**Estimated Time**: 2-3 hours

Files:
- **Safe Pythagorean Operations** (4 files):
  - Dlapy2, Dlapy3 (safe sqrt(x²+y²) and sqrt(x²+y²+z²))
  - Slapy2, Slapy3
- **2x2 Problems** (8 files):
  - Dlae2, Slae2 - 2x2 symmetric eigenvalue problem
  - Dlaev2, Slaev2 - 2x2 symmetric eigenvalue problem with vectors
  - Dlas2, Slas2 - SVD of 2x2 triangular matrix
  - Dlasv2, Slasv2 - SVD of 2x2 general matrix
- **Rotation Generation** (4 files):
  - Dlartg, Slartg - Generate Givens rotation
  - Dlarfg, Slarfg - Generate Householder reflector
- **Complex Matrix Operations** (6 files):
  - Dlanv2, Slanv2 - Standardize 2x2 real Schur block
  - Dlag2, Slag2 - Compute eigenvalues of 2x2 generalized problem
  - Dlags2, Slags2 - Compute sine/cosine for generalized problem
- **Miscellaneous** (4 files):
  - Dlamrg, Slamrg - Merge sorted lists
  - Dlarra, Slarra - Compute splitting points

**Implementation Notes**:
- These are fundamental building blocks used throughout LAPACK
- Small matrices (2x2) make testing straightforward
- Establish patterns for wrapper types (doubleW, floatW, intW)

### **TIER 3: Matrix Copy, Scale, and Set Operations** (32 files)
**Complexity**: Simple
**Dependencies**: TIER 1
**Estimated Time**: 2-3 hours

Files:
- **Copy Operations** (4 files):
  - Dlacpy, Slacpy - Copy matrices with format conversion
  - Dlacp2, Slacp2 - Copy real to complex
- **Scale/Set Operations** (8 files):
  - Dlascl, Slascl - Scale matrix by scalar
  - Dlaset, Slaset - Set matrix to constant or identity
  - Dlascl2, Slascl2 - Scale by vector
  - Dlabad, Slabad - Safe scaling
- **Permutation/Swap** (8 files):
  - Dlapmt, Slapmt - Permute columns
  - Dlaswp, Slaswp - Swap rows
  - Dlapmr, Slapmr - Permute rows
  - Dlacn2, Slacn2 - Estimate matrix 1-norm
- **Miscellaneous Matrix Utilities** (12 files):
  - Dlarrc, Slarrc - Count eigenvalues in interval
  - Dlarrd, Slarrd - Compute eigenvalue clusters
  - Dlarre, Slarre - Representation tree for eigenvalues
  - Dlarrj, Slarrj - Refinement of eigenvalue subset
  - Dlarrk, Slarrk - One eigenvalue by bisection
  - Dlarrr, Slarrr - Test relative representation

### **TIER 4: Matrix Norms and Condition Numbers** (42 files)
**Complexity**: Moderate
**Dependencies**: TIER 1-3
**Estimated Time**: 3-4 hours

Files:
- **Matrix Norms** (18 files):
  - Dlange, Slange - General matrix norm (1, Inf, Frobenius, Max)
  - Dlansy, Slansy - Symmetric matrix norm
  - Dlansp, Slansp - Symmetric packed matrix norm
  - Dlantr, Slantr - Triangular matrix norm
  - Dlantp, Slantp - Triangular packed matrix norm
  - Dlangb, Slangb - General banded matrix norm
  - Dlansb, Slansb - Symmetric banded matrix norm
  - Dlangt, Slangt - Tridiagonal matrix norm
  - Dlanst, Slanst - Symmetric tridiagonal matrix norm
- **Condition Number Estimation** (11 files):
  - Dgecon, Sgecon - General matrix condition (1-norm or Inf-norm)
  - Dpocon, Spocon - Positive definite condition
  - Dppcon, Sppcon - Positive definite packed condition
  - Dpbcon, Spbcon - Positive definite banded condition
  - Dtrcon, Strcon - Triangular matrix condition
  - Dtpcon, Stpcon - Triangular packed condition
  - Dgtcon, Sgtcon - Tridiagonal condition
  - Dptcon, Sptcon - Positive definite tridiagonal condition
- **Equilibration** (8 files):
  - Dgeequ, Sgeequ - Row/column scaling for general matrices
  - Dpoequ, Spoequ - Scaling for symmetric positive definite
  - Dgbequ, Sgbequ - Scaling for banded matrices
  - Dpbequ, Spbequ - Scaling for banded positive definite
- **Other** (5 files):
  - Dlassq, Slassq - Update sum of squares representation
  - Dsum1, Ssum1 - Sum of absolute values
  - Dzsum1 - Complex sum of absolute values

**Implementation Notes**:
- Norms are fundamental metrics used throughout
- Condition numbers require factorizations (defer some tests to later)
- Equilibration routines prepare matrices for better numerical stability

### **TIER 5: Householder and Givens Operations** (16 files)
**Complexity**: Moderate
**Dependencies**: TIER 2
**Estimated Time**: 2-3 hours

Files:
- **Householder Reflectors** (8 files):
  - Dlarf, Slarf - Apply Householder reflector
  - Dlarfb, Slarfb - Apply block reflector
  - Dlarft, Slarft - Form triangular factor of block reflector
  - Dlarfx, Slarfx - Apply elementary reflector (small matrices)
- **Givens Rotations** (8 files):
  - Dlartv, Slartv - Apply vector of Givens rotations
  - Dlargv, Slargv - Generate vector of Givens rotations
  - Dlar1v, Slar1v - Compute one eigenvector (MRRR)
  - Dlar2v, Slar2v - Apply vector of Givens rotations to 2 vectors

**Implementation Notes**:
- Core operations for orthogonal transformations
- Used extensively in QR, eigenvalue, SVD algorithms
- Build on Dlartg/Dlarfg from TIER 2

### **TIER 6: Basic Factorizations** (36 files)
**Complexity**: Moderate to Complex
**Dependencies**: TIER 1-5
**Estimated Time**: 4-5 hours

Files:
- **LU Factorization** (8 files):
  - Dgetrf, Sgetrf - General LU factorization
  - Dgbtrf, Sgbtrf - Banded LU factorization
  - Dgttrf, Sgttrf - Tridiagonal LU factorization
  - Dgetrf2, Sgetrf2 - LU recursive variant
- **Cholesky Factorization** (12 files):
  - Dpotrf, Spotrf - Positive definite Cholesky
  - Dpotrf2, Spotrf2 - Cholesky recursive variant
  - Dpptrf, Spptrf - Packed Cholesky
  - Dpbtrf, Spbtrf - Banded Cholesky
  - Dpttrf, Spttrf - Tridiagonal Cholesky
  - Dsterf, Ssterf - Symmetric tridiagonal eigenvalues (no vectors)
- **QR Factorization** (8 files):
  - Dgeqrf, Sgeqrf - General QR factorization
  - Dgeqr2, Sgeqr2 - QR unblocked
  - Dgelqf, Sgelqf - LQ factorization
  - Dgelq2, Sgelq2 - LQ unblocked
- **QL/RQ Factorization** (8 files):
  - Dgeqlf, Sgeqlf - QL factorization
  - Dgeql2, Sgeql2 - QL unblocked
  - Dgerqf, Sgerqf - RQ factorization
  - Dgerq2, Sgerq2 - RQ unblocked

**Implementation Notes**:
- These are the workhorses of linear algebra
- Start with dense versions (easier), then banded/tridiagonal
- Unblocked (*2) variants are simpler than blocked versions

### **TIER 7: Triangular Operations and Solves** (40 files)
**Complexity**: Moderate
**Dependencies**: TIER 6
**Estimated Time**: 3-4 hours

Files:
- **Triangular Solve** (12 files):
  - Dtrtrs, Strtrs - Solve triangular system
  - Dtptrs, Stptrs - Solve triangular packed
  - Dtbtrs, Stbtrs - Solve triangular banded
  - Dgetrs, Sgetrs - Solve using LU factorization
  - Dpotrs, Spotrs - Solve using Cholesky
  - Dpptrs, Spptrs - Solve packed Cholesky
  - Dpbtrs, Spbtrs - Solve banded Cholesky
  - Dpttrs, Spttrs - Solve tridiagonal Cholesky
  - Dgttrs, Sgttrs - Solve tridiagonal LU
- **Triangular Inverse** (6 files):
  - Dtrtri, Strtri - Invert triangular matrix
  - Dtptri, Stptri - Invert triangular packed
  - Dgetri, Sgetri - Invert using LU
  - Dpotri, Spotri - Invert using Cholesky
  - Dpptri, Spptri - Invert packed Cholesky
- **Refinement** (12 files):
  - Dgerfs, Sgerfs - Refine general linear system
  - Dgbrfs, Sgbrfs - Refine banded system
  - Dgtrfs, Sgtrfs - Refine tridiagonal system
  - Dporfs, Sporfs - Refine positive definite
  - Dpprfs, Spprfs - Refine packed positive definite
  - Dpbrfs, Spbrfs - Refine banded positive definite
  - Dptrfs, Sptrfs - Refine tridiagonal positive definite
  - Dtrrfs, Strrfs - Refine triangular
  - Dtprfs, Stprfs - Refine triangular packed
  - Dtbrfs, Stbrfs - Refine triangular banded
- **Other Triangular** (10 files):
  - Dlauu2, Slauu2 - U'*U or L*L' for triangular
  - Dlauum, Slauum - Blocked version of Dlauu2
  - Dtrti2, Strti2 - Triangular inverse unblocked
  - Dlarzt, Slarzt - Form block reflector for triangular

**Implementation Notes**:
- Most use factorizations from TIER 6
- Refinement routines improve accuracy iteratively
- Can group by matrix storage type

### **TIER 8: Linear System Solvers** (61 files)
**Complexity**: Moderate (mostly driver routines)
**Dependencies**: TIER 6-7
**Estimated Time**: 4-5 hours

Files:
- **General Dense** (6 files):
  - Dgesv, Sgesv - Simple driver (factor + solve)
  - Dgesvx, Sgesvx - Expert driver (equilibrate + factor + solve + refine + condition)
  - Dsgesv, Dsgesv - Mixed precision solve
- **General Banded** (6 files):
  - Dgbsv, Sgbsv - Simple driver
  - Dgbsvx, Sgbsvx - Expert driver
  - Dsbgv, Ssbgv - Symmetric banded generalized eigenvalue
- **Tridiagonal** (6 files):
  - Dgtsv, Sgtsv - Simple driver
  - Dgtsvx, Sgtsvx - Expert driver
  - Dtgsen, Stgsen - Reorder generalized Schur form
- **Positive Definite Dense** (6 files):
  - Dposv, Sposv - Simple driver
  - Dposvx, Sposvx - Expert driver
  - Dsposv, Dsposv - Mixed precision
- **Positive Definite Packed** (6 files):
  - Dppsv, Sppsv - Simple driver
  - Dppsvx, Sppsvx - Expert driver
- **Positive Definite Banded** (6 files):
  - Dpbsv, Spbsv - Simple driver
  - Dpbsvx, Spbsvx - Expert driver
- **Positive Definite Tridiagonal** (6 files):
  - Dptsv, Sptsv - Simple driver
  - Dptsvx, Sptsvx - Expert driver
- **Symmetric Indefinite Dense** (8 files):
  - Dsysv, Ssysv - Simple driver
  - Dsysvx, Ssysvx - Expert driver
  - Dsytrf, Ssytrf - Bunch-Kaufman factorization
  - Dsytrs, Ssytrs - Solve using Bunch-Kaufman
  - Dsytri, Ssytri - Inverse using Bunch-Kaufman
- **Symmetric Indefinite Packed** (8 files):
  - Dspsv, Sspsv - Simple driver
  - Dspsvx, Sspsvx - Expert driver
  - Dsptrf, Ssptrf - Bunch-Kaufman packed
  - Dsptrs, Ssptrs - Solve packed
  - Dsptri, Ssptri - Inverse packed
- **Triangular** (3 files):
  - Dtrtrs, Strtrs - Already in TIER 7
  - Dtpcon, Stpcon - Already in TIER 4

**Implementation Notes**:
- Drivers combine factorization + solve + optional refinement
- Expert drivers add equilibration and condition estimation
- Mixed precision uses single for speed, double for refinement

### **TIER 9: Orthogonal Matrix Generation** (48 files)
**Complexity**: Moderate
**Dependencies**: TIER 5-6
**Estimated Time**: 3-4 hours

Files:
- **QR Generation** (8 files):
  - Dorgqr, Sorgqr - Generate Q from QR (blocked)
  - Dorg2r, Sorg2r - Generate Q from QR (unblocked)
  - Dormqr, Sormqr - Apply Q from QR (blocked)
  - Dorm2r, Sorm2r - Apply Q from QR (unblocked)
- **LQ Generation** (8 files):
  - Dorglq, Sorglq - Generate Q from LQ (blocked)
  - Dorgl2, Sorgl2 - Generate Q from LQ (unblocked)
  - Dormlq, Sormlq - Apply Q from LQ (blocked)
  - Dorml2, Sorml2 - Apply Q from LQ (unblocked)
- **QL Generation** (8 files):
  - Dorgql, Sorgql - Generate Q from QL (blocked)
  - Dorg2l, Sorg2l - Generate Q from QL (unblocked)
  - Dormql, Sormql - Apply Q from QL (blocked)
  - Dorm2l, Sorm2l - Apply Q from QL (unblocked)
- **RQ Generation** (8 files):
  - Dorgrq, Sorgrq - Generate Q from RQ (blocked)
  - Dorgr2, Sorgr2 - Generate Q from RQ (unblocked)
  - Dormrq, Sormrq - Apply Q from RQ (blocked)
  - Dormr2, Sormr2 - Apply Q from RQ (unblocked)
- **Other Orthogonal** (16 files):
  - Dorgbr, Sorgbr - Generate Q or P^T from bidiagonal reduction
  - Dormbr, Sormbr - Apply Q or P^T from bidiagonal reduction
  - Dorghr, Sorghr - Generate Q from Hessenberg reduction
  - Dormhr, Sormhr - Apply Q from Hessenberg reduction
  - Dorgtr, Sorgtr - Generate Q from tridiagonal reduction
  - Dormtr, Sormtr - Apply Q from tridiagonal reduction
  - Dopmtr, Sopmtr - Apply Q from packed tridiagonal reduction
  - Dopgtr, Sopgtr - Generate Q from packed tridiagonal reduction

**Implementation Notes**:
- *org* routines generate explicit orthogonal matrices
- *orm* routines apply without forming explicitly (more efficient)
- Unblocked (*2*) versions simpler than blocked

### **TIER 10: Reduction to Standard Forms** (18 files)
**Complexity**: Moderate to Complex
**Dependencies**: TIER 5-6
**Estimated Time**: 3-4 hours

Files:
- **Hessenberg Reduction** (4 files):
  - Dgehrd, Sgehrd - Reduce to upper Hessenberg form
  - Dgehd2, Sgehd2 - Hessenberg unblocked
- **Tridiagonal Reduction** (8 files):
  - Dsytrd, Ssytrd - Symmetric to tridiagonal (blocked)
  - Dsytd2, Ssytd2 - Symmetric to tridiagonal (unblocked)
  - Dsptrd, Ssptrd - Symmetric packed to tridiagonal
  - Dsbtrd, Ssbtrd - Symmetric banded to tridiagonal
- **Bidiagonal Reduction** (6 files):
  - Dgebrd, Sgebrd - General to bidiagonal (blocked)
  - Dgebd2, Sgebd2 - General to bidiagonal (unblocked)
  - Dgbbrd, Sgbbrd - Banded to bidiagonal

**Implementation Notes**:
- These reduce matrices to simpler forms for eigenvalue/SVD algorithms
- Use Householder reflectors extensively
- Unblocked versions simpler starting point

### **TIER 11: Least Squares Problems** (11 files)
**Complexity**: Moderate
**Dependencies**: TIER 6, 9
**Estimated Time**: 2-3 hours

Files:
- **QR-based** (4 files):
  - Dgels, Sgels - Solve over/underdetermined using QR or LQ
  - Dgelsx, Sgelsx - Complete orthogonal factorization (deprecated)
- **Rank-revealing** (2 files):
  - Dgelsy, Sgelsy - Complete orthogonal factorization (QR with pivoting)
- **SVD-based** (3 files):
  - Dgelss, Sgelss - SVD approach
  - Dgelsd, Sgelsd - Divide-and-conquer SVD
- **Expert** (2 files):
  - Dgglse, Sgglse - Linear equality-constrained least squares
  - Dggglm, Sggglm - Generalized linear regression model

**Implementation Notes**:
- Dgels most common (uses QR from TIER 6)
- SVD-based more robust for rank-deficient problems
- Can start with Dgels, defer complex variants

### **TIER 12: Bidiagonal SVD** (8 files)
**Complexity**: Complex
**Dependencies**: TIER 2, 5, 10
**Estimated Time**: 2-3 hours

Files:
- **QR Iteration** (2 files):
  - Dbdsqr, Sbdsqr - SVD of bidiagonal via QR
- **Divide-and-Conquer** (2 files):
  - Dbdsdc, Sbdsdc - SVD of bidiagonal via divide-and-conquer
- **Support** (4 files):
  - Dlasq1-6, Slasq1-6 - DQDS algorithm for singular values
  - Dlasdt, Slasdt - Tree for divide-and-conquer

**Implementation Notes**:
- These are the core SVD engines
- Dbdsqr is classical QR iteration
- Dbdsdc faster for large matrices but more complex

### **TIER 13: General Matrix SVD** (8 files)
**Complexity**: Moderate (drivers)
**Dependencies**: TIER 10, 12
**Estimated Time**: 2-3 hours

Files:
- **Standard SVD** (4 files):
  - Dgesvd, Sgesvd - SVD via QR iteration
  - Dgesdd, Sgesdd - SVD via divide-and-conquer (faster)
- **Generalized SVD** (4 files):
  - Dggsvd, Sggsvd - Generalized SVD (deprecated)
  - Dggsvd3, Sggsvd3 - Generalized SVD (new version)
  - Dggsvp, Sggsvp - Preprocessing for GSVD
  - Dggsvp3, Sggsvp3 - Preprocessing (new version)

**Implementation Notes**:
- Dgesvd/Dgesdd are main drivers
- Reduce to bidiagonal (TIER 10), then compute SVD (TIER 12)
- Generalized SVD for pairs of matrices

### **TIER 14: Tridiagonal Eigenvalue Problems** (20 files)
**Complexity**: Moderate to Complex
**Dependencies**: TIER 2, 5, 10
**Estimated Time**: 3-4 hours

Files:
- **All Eigenvalues** (8 files):
  - Dsteqr, Ssteqr - QR iteration
  - Dstedc, Sstedc - Divide-and-conquer
  - Dsterf, Ssterf - No eigenvectors (TIER 6)
  - Dstev, Sstev - Simple driver (reduce + solve)
  - Dstevd, Sstevd - Driver with divide-and-conquer
  - Dstevr, Sstevr - Driver with MRRR
  - Dstevx, Sstevx - Driver with subset selection
- **Subset of Eigenvalues** (6 files):
  - Dstebz, Sstebz - Bisection for subset
  - Dstein, Sstein - Inverse iteration for eigenvectors
  - Dstemr, Sstemr - MRRR algorithm (most robust)
- **Generalized** (6 files):
  - Dstegr, Sstegr - MRRR for standard problem
  - Dpteqr, Spteqr - QR for positive definite tridiagonal

**Implementation Notes**:
- Dsteqr is classical QR iteration
- Dstedc faster via divide-and-conquer
- Dstemr most recent and robust (MRRR algorithm)

### **TIER 15: Symmetric Eigenvalue Problems** (34 files)
**Complexity**: Moderate (mostly drivers)
**Dependencies**: TIER 9, 10, 14
**Estimated Time**: 3-4 hours

Files:
- **Symmetric Dense** (10 files):
  - Dsyev, Ssyev - Simple driver (QR iteration)
  - Dsyevd, Ssyevd - Divide-and-conquer driver
  - Dsyevr, Ssyevr - MRRR driver
  - Dsyevx, Ssyevx - Subset driver
  - Dsbev, Ssbev - Banded simple driver
- **Symmetric Packed** (6 files):
  - Dspev, Sspev - Simple driver
  - Dspevd, Sspevd - Divide-and-conquer
  - Dspevx, Sspevx - Subset driver
- **Symmetric Banded** (6 files):
  - Dsbev, Ssbev - Simple driver
  - Dsbevd, Ssbevd - Divide-and-conquer
  - Dsbevx, Ssbevx - Subset driver
- **Generalized Symmetric** (12 files):
  - Dsygv, Ssygv - Dense generalized (A*x = lambda*B*x)
  - Dsygvd, Ssygvd - Generalized divide-and-conquer
  - Dsygvx, Ssygvx - Generalized subset
  - Dspgv, Sspgv - Packed generalized
  - Dspgvd, Sspgvd - Packed divide-and-conquer
  - Dspgvx, Sspgvx - Packed subset
  - Dsbgv, Ssbgv - Banded generalized
  - Dsbgvd, Ssbgvd - Banded divide-and-conquer
  - Dsbgvx, Ssbgvx - Banded subset

**Implementation Notes**:
- All reduce to tridiagonal (TIER 10), then solve (TIER 14)
- *evd variants fastest (divide-and-conquer)
- *evr variants most robust (MRRR)
- Generalized problems require Cholesky factorization of B

### **TIER 16: Non-Symmetric Eigenvalue Problems** (28 files)
**Complexity**: Complex
**Dependencies**: TIER 9, 10, 15
**Estimated Time**: 4-5 hours

Files:
- **Standard Non-Symmetric** (8 files):
  - Dgeev, Sgeev - Eigenvalues + eigenvectors
  - Dgeevx, Sgeevx - Expert driver (balance + compute + condition)
  - Dgees, Sgees - Schur form
  - Dgeesx, Sgeesx - Expert Schur driver
- **Balancing** (4 files):
  - Dgebal, Sgebal - Balance matrix for eigenvalue computation
  - Dgebak, Sgebak - Transform eigenvectors back
- **Hessenberg QR** (6 files):
  - Dhseqr, Shseqr - Eigenvalues of Hessenberg via QR
  - Dlaqr0, Slaqr0 - Aggressive early deflation QR (new)
  - Dlaqr1, Slaqr1 - Small QR block
  - Dlaqr2-5, Slaqr2-5 - Support for aggressive deflation
- **Eigenvector Computation** (4 files):
  - Dtrevc, Strevc - Eigenvectors of triangular/quasi-triangular
  - Dtrevc3, Strevc3 - Level-3 BLAS version
- **Generalized Non-Symmetric** (6 files):
  - Dggev, Sggev - Generalized eigenvalues
  - Dggevx, Sggevx - Expert generalized driver
  - Dgges, Sgges - Generalized Schur
  - Dggesx, Sggesx - Expert generalized Schur
  - Dhgeqz, Shgeqz - QZ algorithm for generalized problem
  - Dtgevc, Stgevc - Eigenvectors for generalized problem

**Implementation Notes**:
- Most complex LAPACK routines
- Reduce to Hessenberg (TIER 10), then QR iteration
- Generalized problems use QZ algorithm
- Can defer aggressive deflation variants (*laqr*) initially

### **TIER 17: Divide-and-Conquer Support Routines** (150+ files)
**Complexity**: Very Complex
**Dependencies**: All previous tiers
**Estimated Time**: 8-10 hours

Files (grouped by algorithm):
- **Symmetric Divide-and-Conquer** (50+ files):
  - Dlaed0-9, Slaed0-9 - Secular equation solver stages
  - Dlaeda, Slaeda - Support for secular equation
  - Dlaed*, Slaed* - Numerous support routines
- **SVD Divide-and-Conquer** (50+ files):
  - Dlasd0-9, Slasd0-9 - SVD divide-and-conquer stages
  - Dlasda, Slasda - Support for SVD
  - Dlasd*, Slasd* - Numerous support routines
- **QR Iteration Support** (30+ files):
  - Dlaqr*, Slaqr* - Aggressive early deflation
  - Dlahr2, Slahr2 - Hessenberg reduction in panel
  - Dlahqr, Slahqr - Hessenberg QR (old version)
- **MRRR Algorithm** (20+ files):
  - Dlarr*, Slarr* - Multiple relatively robust representations
  - Dlarrv, Slarrv - Compute eigenvectors via MRRR
  - Dlarrf, Slarrf - Support for MRRR

**Implementation Notes**:
- These are internal support routines for advanced algorithms
- Most users never call these directly
- Can defer to final phase or skip if time-constrained
- Many are only called by specific high-level drivers

### **TIER 18: Miscellaneous and Specialized** (50+ files)
**Complexity**: Varies
**Dependencies**: Varies
**Estimated Time**: 3-5 hours

Files:
- **Generalized Linear Problems** (6 files):
  - Dggbak, Sggbak - Backward transformation for generalized problem
  - Dggbal, Sggbal - Balance matrix pair
  - Dtgexc, Stgexc - Reorder Schur form
  - Dtgsen, Stgsen - Reorder and condition estimates
  - Dtgsja, Stgsja - Jacobi-type SVD
  - Dtgsyl, Stgsyl - Generalized Sylvester equation
- **Sylvester and Lyapunov** (4 files):
  - Dtrsyl, Strsyl - Sylvester equation solver
  - Dtrsna, Strsna - Condition numbers for eigenvectors
  - Dtrsen, Strsen - Reorder Schur form with condition
  - Dtrexc, Strexc - Reorder Schur form
- **Special Utilities** (40+ files):
  - Dlapll, Slapll - Plane rotation to align vectors
  - Dlaln2, Slaln2 - Solve 1x1 or 2x2 system
  - Dlasy2, Slasy2 - Solve Sylvester 2x2 system
  - Dlaqps, Slaqps - QR with column pivoting
  - Dlaqp2, Slaqp2 - QR pivoting unblocked
  - Dlaqtr, Slaqtr - Solve quasi-triangular system
  - Dlatzm, Slatzm - Apply elementary reflector to pencil
  - Dlatrz, Slatrz - RZ factorization (trapezoidal)
  - Dtzrzf, Stzrzf - RZ factorization driver
  - Many more specialized routines...

**Implementation Notes**:
- Mix of utility and specialized algorithms
- Some are rarely used except by specific drivers
- Can implement opportunistically based on dependencies

## Implementation Workflow

### Step 1: Enhance LAPACKTest Base Class
**Estimated Time**: 30 minutes

Add to `/workspaces/netlib/lapack/src/test/java/dev/ludovic/netlib/lapack/LAPACKTest.java`:
- Test data generation utilities
- Common assertion helpers
- Matrix/vector initialization methods
- Standard test sizes (N=50, small matrices for 2x2 problems, etc.)

Example additions:
```java
protected final int N = 50;
protected final int N_SMALL = 10;

// Arrays for testing
protected final int[] intArray1 = generateIntArray(N, 1);
protected final double[] dArray1 = generateDoubleArray(N, 1.0);
protected final float[] sArray1 = generateFloatArray(N, 1.0f);

// Matrices
protected final double[] dMatrix = generateMatrix(N, N);
protected final double[] dSymmetricMatrix = generateSymmetricMatrix(N);
protected final double[] dPositiveDefiniteMatrix = generatePositiveDefiniteMatrix(N);

// Utility methods
protected static int[] generateIntArray(int n, int seed);
protected static double[] generateDoubleArray(int n, double scale);
protected static float[] generateFloatArray(int n, float scale);
protected static double[] generateMatrix(int m, int n);
protected static double[] generateSymmetricMatrix(int n);
protected static double[] generatePositiveDefiniteMatrix(int n);

// Assertion helpers
protected static void assertArrayEquals(double[] expected, double[] actual, double epsilon);
protected static void assertArrayEquals(float[] expected, float[] actual, float epsilon);
```

### Step 2: Implement Tests by Tier
**Approach**: Work through tiers sequentially, parallelizing within tiers

For each tier:
1. **Group Related Tests** - Batch 10-20 similar tests together
2. **Implement Reference Pattern** - Start with one test as template
3. **Parallelize Implementation** - Use Task tool for concurrent implementation
4. **Run Tests** - Validate with `mvn clean test -pl lapack`
5. **Fix Issues** - Address any failures before moving to next tier

### Step 3: Handle Complex Cases
**Special Considerations**:

1. **Wrapper Types (org.netlib.util)**:
   - intW, doubleW, floatW for pass-by-reference
   - Initialize before calling, read after calling

2. **Matrix Storage Formats**:
   - Dense: standard column-major arrays
   - Packed: triangular/symmetric in packed format
   - Banded: diagonal storage

3. **LAPACK Return Codes**:
   - INFO parameter: 0 = success, <0 = illegal argument, >0 = algorithm failure
   - Some tests may intentionally trigger non-zero INFO

4. **Epsilon Tolerances**:
   - depsilon = 1e-15 for double precision
   - sepsilon = 1e-6 for single precision
   - May need larger epsilon for iterative algorithms

### Step 4: Validation Strategy

After implementing each tier:
```bash
mvn clean test -pl lapack -Dtest="Dlapy2Test,Slapy2Test" # Test specific files
mvn clean test -pl lapack # Full suite
```

Track progress:
```bash
# Count remaining stubs
grep -r "assumeTrue(false)" lapack/src/test/java | wc -l

# Count implemented tests
mvn test -pl lapack 2>&1 | grep "Tests run:"
```

## Success Criteria

1. **All 724 test files implemented** - No `assumeTrue(false)` stubs remaining
2. **All tests pass** - `mvn clean test -pl lapack` shows BUILD SUCCESS
3. **Comprehensive coverage** - Each test compares against F2jLAPACK reference
4. **Follows established patterns** - Consistent with BLAS and ARPACK implementations

## Risk Mitigation

### Challenge: Scale (724 files vs 58 for ARPACK)
**Mitigation**:
- Tiered approach allows incremental progress
- Many tests follow similar patterns (D/S variants, dense/packed/banded variants)
- Can parallelize implementation within tiers
- Focus on high-value tiers first (utilities, factorizations, solvers)

### Challenge: Complex Algorithms
**Mitigation**:
- Start with simple utilities to build confidence
- Use F2jLAPACK as oracle - don't need to understand algorithms deeply
- Defer complex divide-and-conquer support routines to later tiers
- Can skip rarely-used specialized routines if time-constrained

### Challenge: Test Data Generation
**Mitigation**:
- Build robust utilities in LAPACKTest base class
- Special-case positive definite matrices (A = B'*B)
- Use small problem sizes for speed
- Can use random data for most tests

## Estimated Timeline

| Phase | Tiers | Files | Time | Cumulative |
|-------|-------|-------|------|------------|
| Foundation | 1-3 | 83 | 5-8 hours | 83 files |
| Core Operations | 4-7 | 164 | 12-16 hours | 247 files |
| Solvers | 8 | 61 | 4-5 hours | 308 files |
| Orthogonal | 9-10 | 66 | 6-8 hours | 374 files |
| Advanced | 11-16 | 209 | 16-21 hours | 583 files |
| Support | 17-18 | 141 | 11-15 hours | 724 files |

**Total Estimated Time**: 54-73 hours

With parallelization and pattern reuse, realistic timeline: **2-3 full work days**

## Implementation Priority

If time-constrained, prioritize:
1. **TIER 1-6** (Foundation + Factorizations) - 219 files, most important
2. **TIER 7-8** (Solves + Drivers) - 101 files, high user visibility
3. **TIER 9-11** (Orthogonal + Least Squares) - 77 files, common operations
4. **TIER 12-16** (SVD + Eigenvalues) - 97 files, advanced but valuable
5. **TIER 17-18** (Support) - 200+ files, internal routines (can defer)

## Next Steps

After plan approval:
1. Enhance LAPACKTest.java base class with utilities
2. Implement TIER 1 (utilities) to establish patterns
3. Proceed through tiers systematically
4. Monitor progress with regular test runs
5. Address failures promptly before moving forward
