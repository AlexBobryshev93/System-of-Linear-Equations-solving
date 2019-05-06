class QMatrix {
	double[][] M;
	
	QMatrix(double[][] Matr) {
		for (int i = 0; i < Matr.length; i++) {
			if (Matr.length != Matr[i].length) {
				System.out.println("Error: Wrong size!\n");
				return;
			}
		}
		
		M = new double[Matr.length][Matr[0].length];
		for (int i = 0; i < Matr.length; i++)
			for (int j = 0; j < Matr[i].length; j++) {
				M[i][j] = Matr[i][j];
			}
	}
	
	QMatrix(QMatrix Matr) {
		M = new double[Matr.M.length][Matr.M[0].length];
		for (int i = 0; i < Matr.M.length; i++)
			for (int j = 0; j < Matr.M[i].length; j++) {
				M[i][j] = Matr.M[i][j];
			}
	}
	
	QMatrix invMat() {
		QMatrix Result;
		double[][] Inv = new double[M.length][M[0].length];
		double det;
		
		QMatrix C = new QMatrix(M);
		det = C.det();
		C = C.adjMat();
		C = C.transpMat();
		
		for (int i = 0; i < M.length; i++)
			for (int j = 0; j < M[i].length; j++) {
				Inv[i][j] = (1 / det) * C.M[i][j];
			}
		
		Result = new QMatrix(Inv);
		return Result;
	} 
	
	QMatrix transpMat() {
		QMatrix Result;
		double[][] Transp = new double[M.length][M[0].length];
		
		for (int i = 0; i < M.length; i++)
			for (int j = 0; j < M[i].length; j++) {
				Transp[i][j] = M[j][i];
			}
		
		Result = new QMatrix(Transp);
		return Result;
	} 
	
	QMatrix adjMat() {
		QMatrix Result;
		double[][] Adj = new double[M.length][M[0].length];
		
		for (int i = 0; i < M.length; i++)
			for (int j = 0; j < M[i].length; j++) {
				QMatrix Minor1 = new QMatrix(M);
				Minor1 = Minor1.minor(i, j);
				Adj[i][j] = Math.pow(-1, i + j) * Minor1.det();
			}
		
		Result = new QMatrix(Adj);
		return Result;
	} 
	
	QMatrix minor(int ii, int jj) {
		QMatrix Result;
		double[][] Minor = new double[M.length - 1][M[0].length - 1];
		
		for (int i = 0, iminor = 0; i < M.length; i++, iminor++) {
			if (i == ii) {
				i++; 
			} 
			if (i < M.length) {
				for (int j = 0, jminor = 0; j < M[i].length; j++, jminor++) {
					if (j == jj) {
					j++;
					}
					if (j < M[0].length) {
						Minor[iminor][jminor] = M[i][j]; 
					}	
				}
			}
		}
		
		Result = new QMatrix(Minor);
		return Result;
	}
	
	double det() {
		double result = 0;
		if (M.length == 1) result = M[0][0];
		else {
			for (int j = 0; j < M[0].length; j++) {
				QMatrix Minor1 = new QMatrix(M);
				Minor1 = Minor1.minor(0, j);
				result += Math.pow(-1, j) * M[0][j] * Minor1.det(); 
			}
		}
		return result;	
	}
	/*
	void setM(double M, int i, int j) {
		M[i][j] = M;
	}

	double getM(int i, int j) {
		return M[i][j];
	}
	*/
}

