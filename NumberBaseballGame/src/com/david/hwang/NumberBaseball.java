package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* ���� �� �ʱ�ȭ */
		String[] quizArr = new String[] { "0", "0", "0" }; // ��ǻ�Ͱ� ������ ������ �� 3���� �� �迭
		String[] answerArr = new String[] { "0", "0", "0" }; // ����ڰ� ������ 3�ڸ� ���� �� �迭
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		int strike = 0; // strike ����
		int ball = 0; // ball ����
		// int nothing = 0; // nothing ����

		// ��ǻ�� ���� �� ����
		for (int i = 0; i < 3; i++) {
			quizArr[i] = nb.makeRandomNumber(1, 9);
		}
		System.out.println("��ǻ�� ���� �� : " + Arrays.toString(quizArr));

		// ����� �Է� ó��
		Scanner scanner = new Scanner(System.in);
		System.out.println("���ڸ� �Է����ּ��� ex)123 : ");
		String userInputNum = scanner.next();
		System.out.println(userInputNum);

		// ����ڰ� �Է��� ��(���ڿ�)�� �迭�� �ϳ��� �ɰ��� �ִ´�.
		answerArr = userInputNum.split("(?!^)");
		System.out.println("����ڰ� �Է��� �� : " + Arrays.toString(answerArr));
		// System.out.println(nb.makeRandomNumber(1,9));

		// ��ǻ�Ϳ� ������� �� ��
		// if (quizArr[0].equals(answerArr[0])) {
		// strike++;
		// System.out.println("no1");
		// } else if (quizArr[0].equals(answerArr[1])) {
		// ball++;
		// System.out.println("no2");
		// } else if (quizArr[0].equals(answerArr[2])) {
		// ball++;
		// System.out.println("no3");
		// }

		nb.compare(quizArr, answerArr, scoreArr);

		// System.out.println("s : " + strike + " b : " + ball);
		System.out.println("s : " + scoreArr[0] + " b : " + scoreArr[1]);
	}

	// ��ǻ���� ���� ���� ����ڰ� �Է��� ���� ��
	public void compare(String[] quizArr, String[] answerArr, int[] scoreArr) {
		// findStrike(quizArr, answerArr, scoreArr);
		for (int i = 0; i < 3; i++) {
			if (findStrike(quizArr, answerArr, scoreArr, i)) {
				continue;
			} else {
				findBall(quizArr, answerArr, scoreArr, i);
			}
		}
	}

	public boolean findStrike(String[] quizArr, String[] answerArr, int[] scoreArr, int index) {
		if (quizArr[index].equals(answerArr[index])) {
			scoreArr[0]++; // strike++;
			return true;
		} else {
			return false;
		}
	}

	public void findBall(String[] quizArr, String[] answerArr, int[] scoreArr, int index) {
		for (int i = 0; i < 3; i++) {
			if (!(i == index) & quizArr[index].equals(answerArr[i])) {
				scoreArr[1]++; //ball++;
			}
		}
	}

	/*
	 * num1�� ���� ���� ���Ѱ�, num2�� ���� ���� ���Ѱ�. makeRandomNumber(1, 9)�̸�, 1~9������ ������ ���� ��ȯ
	 */
	public String makeRandomNumber(int num1, int num2) {
		// int randomNumber = 0;
		// {
		// randomNumber = (int)(Math.random() * 10); //���� 10�� ���ϰ� int�� ��ȯ. int�� ���� ��ȯ�ϸ�
		// 0���� ���.
		// System.out.println(randomNumber);
		// }while(!(randomNumber == 0 || randomNumber == 10));
		int randomNumber = (int) (Math.random() * (num2 - num1 + 1)) + num1;
		return Integer.toString(randomNumber);
	}
}
