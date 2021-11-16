package co.mz.milleniumbim.challenge.service;

public class MilleniumbimChallengeArrays {

	private int[][] numbers = { { 5, 5, 2, 5, 3, 5, 2, 4, 1, 0 }, { 5, 3, 5, 4, 4, 5, 0, 4, 1, 1 },
			{ 0, 5, 5, 1, 3, 2, 1, 1, 0, 3 }, { 3, 5, 0, 1, 3, 2, 1, 5, 1, 5 }, { 2, 4, 4, 3, 2, 3, 3, 3, 0, 5 },
			{ 0, 0, 1, 2, 2, 5, 0, 5, 1, 0 }, { 4, 0, 0, 4, 1, 5, 1, 1, 3, 3 }, { 3, 0, 5, 2, 2, 0, 5, 2, 2, 5 },
			{ 1, 3, 0, 4, 2, 3, 2, 0, 4, 1 }, { 1, 1, 2, 4, 0, 2, 4, 3, 4, 1 }, { 0, 3, 3, 4, 2, 3, 0, 5, 2, 4 }, 
			{ 0, 2, 0, 4, 5, 1, 2, 3, 0, 3 }, { 5, 0, 4, 0, 5, 5, 4, 4, 1, 1 }, { 5, 4, 5, 0, 0, 1, 0, 1, 5, 1 },
			{ 4, 4, 0, 4, 4, 1, 0, 3, 0, 3 }, { 2, 5, 1, 1, 3, 1, 1, 0, 0, 5 }, { 4, 2, 2, 2, 5, 2, 4, 2, 2, 1 }, 
			{ 1, 0, 4, 4, 1, 2, 5, 4, 2, 5 }, { 2, 0, 5, 3, 4, 3, 2, 5, 2, 1 }, { 1, 4, 5, 0, 5, 3, 5, 4, 1, 3 } };

	int[] arrayControllerCurrent = new int[10];
	int[] arrayControllerBottom = new int[10];

	/**
	 * 
	 * @param row a ser copia para um array de reserva que controla o indice atual
	 */
	private void copyCurrentRow(int row) {
		for (int i = 0; i < 10; i++) {
			arrayControllerCurrent[i] = numbers[row][i];
		}
	}

	/**
	 * 
	 * @param row a ser copiado para um array de reserva que controla o proximo indice
	 */
	private void copyBottomRow(int row) {
		for (int i = 0; i < 10; i++) {
			arrayControllerBottom[i] = numbers[row][i];
		}
	}

	/**
	 * Copia os valores de uma linha para outra
	 * @param bottom array contendo os valores de linha de baixo
	 */
	private void copyBottomToCurrent(int[] bottom) {
		for (int i = 0; i < 10; i++) {
			arrayControllerCurrent[i] = bottom[i];
		}
	}

	public void organizerArray() {
		copyCurrentRow(0);
		boolean stop = false;
		while (!stop) {
			deslize();
			int counter = 0;
			for (int i = 0; i < 20; i++) {
				if (i < 19) copyBottomRow(i + 1);
				for (int j = 0; j < 10; j++) {
					if (j < 9) {//Evita que se compare com posicao 10
						//Compara entre colunas
						if ((arrayControllerCurrent[j] == numbers[i][j + 1]) && (numbers[i][j + 1] != 6)) {
							numbers[i][j] = 6;
							numbers[i][j + 1] = 6;
							counter++;
						}
					}

					if (i < 19) {// Evita que se compare com posicao 20
						//Compara entre linhas
						if ((arrayControllerCurrent[j] == numbers[i + 1][j]) && (numbers[i + 1][j] != 6)) {
							numbers[i][j] = 6;
							numbers[i + 1][j] = 6;
							counter++;
						}
					}
					deslize();//realize o deslize caso o valor que se encontra na posicao de baixo de vazio
				}
				copyBottomToCurrent(arrayControllerBottom);// copia valores de um  array para outro
			}
			if (counter == 0) stop = true;// termina o loop
		}
	}

	/*
	 * Realiza o deslize
	 */
	private void deslize() {
		boolean stop = true;
		while (stop) {
			int changes = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 10; j++) {
					if (numbers[i][j] != 6 && numbers[i + 1][j] == 6) {
						numbers[i + 1][j] = numbers[i][j];
						numbers[i][j] = 6;
						changes++;
					}
				}
			}
			if (changes == 0) stop = false;
		}
	}

	public void printArrayBidimension() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("[%d] ", numbers[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		MilleniumbimChallengeArrays arrayService = new MilleniumbimChallengeArrays();

		System.out.println("Fornecido:");
		arrayService.printArrayBidimension();
		
		System.out.println("\nResolvido :");
		arrayService.organizerArray();
		arrayService.printArrayBidimension();
	}

}
