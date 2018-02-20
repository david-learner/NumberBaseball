package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	public static void main(String[] args) {
		/* ���� �� �ʱ�ȭ */
		int[] quizArr = { 0, 0, 0 }; // ��ǻ�Ͱ� ������ ������ �� 3���� �� �迭
		int[] answerArr = { 0, 0, 0 }; // ����ڰ� �Է��� 3�ڸ� ���� �� �迭
		int[] scoreArr = { 0, 0 }; // [0]:strike, [1]:ball

		// NumberBaseball nb = new NumberBaseball(); // �ν��Ͻ��� ������� �ʰ� ����
		quizArr = makeRandomNumberArr(); // ��ǻ�� ���� �� �迭 ����

		while (scoreArr[0] < 3) {
			init(answerArr); // ���� ������� �Է°� �ʱ�ȭ
			answerArr = splitNumber(inputNumber());
			scoreArr = compare(quizArr, answerArr);
			result(scoreArr);
		}
	}

	public static int inputNumber() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("���ڸ� �Է����ּ��� ex)123 : ");
		int userInputNumber = scanner.nextInt();
		//scanner.close();
		return userInputNumber;
	}

	public static int[] splitNumber(int userInputNumber) {
		int[] userInputNumberArr = { 0, 0, 0 };
		userInputNumberArr[0] = userInputNumber / 100; // �����ڸ�
		userInputNumberArr[1] = (userInputNumber / 10) % 10; // �����ڸ�
		userInputNumberArr[2] = userInputNumber % 10; // �����ڸ�
		return userInputNumberArr;
	}

	// ������ �Էµ� ����� �Է� �� �� ���� �ʱ�ȭ
	public static void init(int[] initiatedArr) {
		Arrays.fill(initiatedArr, 0);
	}

	// ��ǻ���� ���� ���� ����ڰ� �Է��� ���� ��
	public static int[] compare(int[] quizArr, int[] answerArr) {
		int[] scoreArr = { 0, 0 };
		for (int i = 0; i < 3; i++) {
			if (isStrike(quizArr, answerArr, i)) {
				scoreArr[0]++;
			}else if (isBall(quizArr, answerArr, i)) {
				scoreArr[1]++;
			}
		}
		return scoreArr;
	}

	public static boolean isStrike(int[] quizArr, int[] answerArr, int index) {
		if (quizArr[index] == answerArr[index]) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBall(int[] quizArr, int[] answerArr, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(i == index) && quizArr[index] == answerArr[i]) {
				return true;
			}
		}
		return false;
	}

	public static void result(int[] scoreArr) {
		// 3��Ʈ����ũ(����)�� ���� üũ�ϰ� �������� "n��Ʈ����ũ"�� ���. �ݴ�� �Ǹ� ������ 3��Ʈ����ũ ���.
		if (scoreArr[0] == 3) {
			System.out.print("3���� ���ڸ� ��� �����̽��ϴ�! ���� ����");
		} else if (scoreArr[0] != 0) {
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


	// num1�� ���� ���� ���Ѱ�, num2�� ���� ���� ���Ѱ�. makeRandomNumber(1, 9)�̸�, 1~9������ ������ ���� ��ȯ
	public static int makeRandomNumber(int min, int max) {
		int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNumber;
	}

	public static int[] makeRandomNumberArr() {
		int[] quizArr = { 0, 0, 0 };
		int randomNumber = 0;
		for (int i = 0; i < 3; i++) {
			do {
				randomNumber = makeRandomNumber(1, 9);
			} while (isOverlap(quizArr, randomNumber, i));
		}
		return quizArr;
	}

	public static boolean isOverlap(int[] quizArr, int randomNumber, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(quizArr[i] == 0) && (quizArr[i] == randomNumber)) {
				return true;
			}
		}
		insert(quizArr, randomNumber, index); // �ߺ����� �ʴ� ����, quiz�迭�� ����
		return false;
	}

	public static void insert(int[] quizArr, int randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
