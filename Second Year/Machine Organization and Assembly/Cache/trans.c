//Brendon Peters: bjpeters@wpi.edu


/* 
 * trans.c - Matrix transpose B = A^T
 *
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */ 
#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);

/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 * This functions is typically partitioned with a Switch Case staememnt or else if else 
 *  statements that hanlde the three cases, 32 X 32, 64 X 64 and 61 X 67. Here the 
*  function executes the same code for any size matrix .... Your job is to specialize it!  
* especially for the 64 X 64 matrix transpose
 */
char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N])
{
    //Using linux commands I was able to find exact cache values
    //Using these values I was able to minimize the number of cache misses
    int L1 = 8;
    int L2 = 64;

    for (int xL2 = 0; xL2 < N; xL2 += L2) {
        for (int yL2 = 0; yL2 < M; yL2 += L2) {
            for (int xL1 = xL2; xL1 < xL2 + L2 && xL1 < N; xL1 += L1) {
                for (int yL1 = yL2; yL1 < yL2 + L2 && yL1 < M; yL1 += L1) {
                    for (int i = xL1; i < xL1 + L1 && i < N; i++) {
                        for (int j = yL1; j < yL1 + L1 && j < M; j++) {
                            if (i != j) {
                                B[j][i] = A[i][j];
                            }
                        }
                        if (xL1 == yL1) {
                            B[i][i] = A[i][i];
                        }
                    }
                }
            }
        }
    }
}

/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";
void trans(int M, int N, int A[N][M], int B[M][N])
{
    int i, j, tmp;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; j++) {
            tmp = A[i][j];
            B[j][i] = tmp;
        }
    }    

}

/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions()
{
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    registerTransFunction(trans, trans_desc); 

}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N])
{
    int i, j;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}
