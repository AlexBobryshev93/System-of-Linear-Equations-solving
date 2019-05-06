class SoLE {
	QMatrix A;
	double[] X;
	double[] B;
	
	SoLE(QMatrix Matr, double[] B_) {
		if (Matr.M.length != B_.length) System.out.println("Error: Wrong number of coefficients!\n");
			
		A = Matr;
		B = B_;
		X = new double[B.length];
	}
	
	void gaussSolve() {
		QMatrix A0 = new QMatrix(A);
		double[] B0 = new double[B.length];
		double[] T = new double[A.M[0].length + 1]; // free coefficietns require 1 more position in the array
		double coef;
		
		for (int i = 0; i < B.length; i++) B0[i] = B[i]; 
		System.out.println("Solving with Gauss-Jordan elimination:");
		
		for (int j = 0; j < A.M[0].length; j++) {
			for (int i = j; i < A.M.length; i++) {
				if (A0.M[i][j] != 0) {
					for (int jj = 0; jj < A.M[0].length; jj++) {
						T[jj] = A0.M[i][jj];
						A0.M[i][jj] = A0.M[j][jj];
						A0.M[j][jj] = T[jj] / T[j];
					}
					T[A.M[0].length] = B0[i];
					B0[i] = B0[j];
					B0[j] = T[A.M[0].length] / T[j];
					break;
				}
			}
			for(int i = j + 1; i < A.M.length; i++) {
				coef = A0.M[i][j] / T[j];
				for (int jj = 0; jj < A.M[0].length; jj++) {
					A0.M[i][jj] -= coef * T[jj];
				}
				B0[i] -= coef * T[A.M[0].length];
			}
		}
		
		for (int i = A.M.length - 1; i > 0; i--) 
			for (int ii = i; ii > 0; ii--) {
				B0[ii - 1] -= A0.M[ii - 1][i] * B0[i];
				A0.M[ii - 1][i] = 0;
			}

		for (int i = 0; i < A.M.length; i++)
			X[i] = B0[i];
	}
	
	void cramerSolve() {
		QMatrix Cram = new QMatrix(A);  
		System.out.println("Solving with Cramer's rule:");
		
		for (int j = 0; j < A.M[0].length; j++) {
			for (int i = 0; i < A.M.length; i++) {
				Cram.M[i][j] = B[i];
			}
			X[j] = Cram.det() / A.det();
			Cram = new QMatrix(A);
		}
	}
	
	void matrixSolve() {
		QMatrix InvM = A.invMat();
		System.out.println("Solving with the invertible matrix:");
		
		for (int i = 0; i < A.M.length; i++) {
			X[i] = 0;
			for (int j = 0; j < A.M[0].length; j++) {
				X[i] += InvM.M[i][j] * B[j];
			}
		}	
	}

}