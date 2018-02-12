package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* ���� �� �ʱ�ȭ */
		String[] quizArr = new String[] { "0", "0", "0" }; // ��ǻ�Ͱ� ������ ������ �� 3���� �� �迭
		String[] answerArr = new String[] { "0", "0", "0" }; // ����ڰ� �Է��� 3�ڸ� ���� �� �迭
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		String randomNumber = "0";

		// ��ǻ�� ���� �� ����
		int i = 0;
		while (i < 3) {
			randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); // ������ ���� ����
			if (nb.isOverlap(quizArr, randomNumber, i)) {
				continue;
			} else {
				i++;
			}
		}

		while (scoreArr[0] < 3) {
			nb.init(answerArr, scoreArr);
			// ����� �Է� ó��
			Scanner scanner = new Scanner(System.in);
			System.out.print("���ڸ� �Է����ּ��� ex)123 : ");
			String userInputNum = scanner.next();

			// ����ڰ� �Է��� ��(���ڿ�)�� �迭�� �ϳ��� �ɰ��� �ִ´�.
			answerArr = userInputNum.split("(?!^)");
			// ��ǻ�Ϳ� ������� �� ��
			nb.compare(quizArr, answerArr, scoreArr);
			nb.result(scoreArr);
		}
	}

	// ����� �Է� ���� ��� �迭�� ���� �迭�� �ʱ�ȭ
	public void init(String[] answerArr, int[] scoreArr) {
		Arrays.fill(answerArr, "0");
		Arrays.fill(scoreArr, 0);
	}

	// ��ǻ���� ���� ���� ����ڰ� �Է��� ���� ��
	public void compare(String[] quizArr, String[] answerArr, int[] scoreArr) {
		for (int i = 0; i < 3; i++) {
			if (isStrike(quizArr, answerArr, scoreArr, i)) {
				continue;
			} else {
				isBall(quizArr, answerArr, scoreArr, i);
			}
		}
	}

	public boolean isStrike(String[] quizArr, String[] answerArr, int[] scoreArr, int index) {
		if (quizArr[index].equals(answerArr[index])) {
			scoreArr[0]++; // strike++;
			return true;
		} else {
			return false;
		}
	}

	public void isBall(String[] quizArr, String[] answerArr, int[] scoreArr, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(i == index) && quizArr[index].equals(answerArr[i])) {
				scoreArr[1]++; // ball++;
			}
		}
	}

	public void result(int[] scoreArr) {
		if (scoreArr[0] != 0) {
			if (scoreArr[0] == 3) {
				System.out.print("3���� ���ڸ� ��� �����̽��ϴ�! ���� ����");
			} else {
				System.out.print(scoreArr[0] + "��Ʈ����ũ ");
			}
		}

		if (scoreArr[1] != 0) {
			System.out.println(scoreArr[1] + "��");
		}

		if ((scoreArr[0] == 0) && (scoreArr[1] == 0)) {
			System.out.println("����");
		}
	}

	/*
	 * num1�� ���� ���� ���Ѱ�, num2�� ���� ���� ���Ѱ�. makeRandomNumber(1, 9)�̸�, 1~9������ ������ ���� ��ȯ
	 */
	public int makeRandomNumber(int min, int max) {
		int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNumber;
	}

	public boolean isOverlap(String[] quizArr, String randomNumber, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(quizArr[i].equals("0")) && (quizArr[i].equals(randomNumber))) {
				return true;
			}
		}
		insert(quizArr, randomNumber, index); // �ߺ����� �ʴ� ����, quiz�迭�� ����
		return false;
	}

	public void insert(String[] quizArr, String randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
