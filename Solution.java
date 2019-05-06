class Solution {
	public static void main(String[] args) {
		double[][] A = {{2, 5, 4, 1}, {1, 3, 2, 1}, {2, 10, 9, 9}, {3, 8, 9, 2}};
		double[] B = {20, 11, 40, 37};
		
		QMatrix A0 = new QMatrix(A);
		SoLE Sys = new SoLE(A0, B);
		
		System.out.println();
		
		Sys.gaussSolve();
		for (int i = 0; i < A.length; i++) System.out.println("x[" + i + "] = " + Sys.X[i]);
		System.out.println();
		
		Sys.cramerSolve();
		for (int i = 0; i < A.length; i++) System.out.println("x[" + i + "] = " + Sys.X[i]);
		System.out.println();
		
		Sys.matrixSolve();
		for (int i = 0; i < A.length; i++) System.out.println("x[" + i + "] = " + Sys.X[i]);
		System.out.println();
	}
}