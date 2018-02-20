package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	/* ���� �� �ʱ�ȭ */
	int[] quizArr = { 0, 0, 0 }; // ��ǻ�Ͱ� ������ ������ �� 3���� �� �迭
	int[] answerArr = { 0, 0, 0 }; // ����ڰ� �Է��� 3�ڸ� ���� �� �迭
	int[] scoreArr = { 0, 0 }; // [0]:strike, [1]:ball
	int randomNumber = 0;

	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball(); // NumberBaseball ��ü ����

		nb.insertRandomNumber(nb.quizArr, nb.randomNumber); // ��ǻ�� ���� �� ����

		while (nb.scoreArr[0] < 3) {
			nb.init(nb.answerArr, nb.scoreArr); // ���� �����
			// ù ��° �Ű������� ����(?)��. ������ ���ϰ��� ������ ���� ���ڴ� ���� �þ��.
			nb.insertNumber(nb.splitNumber(nb.inputNumber()), nb.answerArr);
			nb.compare(nb.quizArr, nb.answerArr, nb.scoreArr);
			nb.result(nb.scoreArr);
		}
	}

	public int inputNumber() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("���ڸ� �Է����ּ��� ex)123 : ");
		int userInputNumber = scanner.nextInt();
		return userInputNumber;
	}

	public int[] splitNumber(int userInputNumber) {
		int[] userInputNumberArr = { 0, 0, 0 };
		userInputNumberArr[0] = userInputNumber / 100; // �����ڸ�
		userInputNumberArr[1] = (userInputNumber / 10) % 10; // �����ڸ�
		userInputNumberArr[2] = userInputNumber % 10; // �����ڸ�
		return userInputNumberArr;
	}

	public void insertNumber(int[] splittedNumberArr, int[] answerArr) {
		System.arraycopy(splittedNumberArr, 0, answerArr, 0, 3);
	}

	// ������ �Էµ� ����� �Է� �� �� ���� �ʱ�ȭ
	public void init(int[] answerArr, int[] scoreArr) {
		Arrays.fill(answerArr, 0);
		Arrays.fill(scoreArr, 0);
	}

	// ��ǻ���� ���� ���� ����ڰ� �Է��� ���� ��
	public void compare(int[] quizArr, int[] answerArr, int[] scoreArr) {
		for (int i = 0; i < 3; i++) {
			if (isStrike(quizArr, answerArr, scoreArr, i)) {
				continue;
			} else {
				isBall(quizArr, answerArr, scoreArr, i);
			}
		}
	}

	public boolean isStrike(int[] quizArr, int[] answerArr, int[] scoreArr, int index) {
		if (quizArr[index] == answerArr[index]) {
			scoreArr[0]++; // strike++;
			return true;
		} else {
			return false;
		}
	}

	public void isBall(int[] quizArr, int[] answerArr, int[] scoreArr, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(i == index) && quizArr[index] == answerArr[i]) {
				scoreArr[1]++; // ball++;
			}
		}
	}

	public void result(int[] scoreArr) {
		//3��Ʈ����ũ(����)�� ���� üũ�ϰ� �������� "n��Ʈ����ũ"�� ���. �ݴ�� �Ǹ� ������ 3��Ʈ����ũ ���.
		if (scoreArr[0] == 3) {
			System.out.print("3���� ���ڸ� ��� �����̽��ϴ�! ���� ����");
		}else if (scoreArr[0] != 0) {
			System.out.print(scoreArr[0] + "��Ʈ����ũ ");
		}

		if (scoreArr[1] != 0) {
			System.out.print(scoreArr[1] + "��");
		}

		if ((scoreArr[0] == 0) && (scoreArr[1] == 0)) {
			System.out.print("����");
		}

		System.out.println("");
	}

	/*
	 * num1�� ���� ���� ���Ѱ�, num2�� ���� ���� ���Ѱ�. makeRandomNumber(1, 9)�̸�, 1~9������ ������ ���� ��ȯ
	 */
	public int makeRandomNumber(int min, int max) {
		int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNumber;
	}

	public void insertRandomNumber(int[] quizArr, int randomNumber) {
		for (int i = 0; i < 3; i++) {
			do {
				randomNumber = makeRandomNumber(1, 9);
			} while (isOverlap(quizArr, randomNumber, i));
		}
	}

	public boolean isOverlap(int[] quizArr, int randomNumber, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(quizArr[i] == 0) && (quizArr[i] == randomNumber)) {
				return true;
			}
		}
		insert(quizArr, randomNumber, index); // �ߺ����� �ʴ� ����, quiz�迭�� ����
		return false;
	}

	public void insert(int[] quizArr, int randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
