package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* ���� �� �ʱ�ȭ */
		String[] quizArr = new String[] { "0", "0", "0" }; // ��ǻ�Ͱ� ������ ������ �� 3���� �� �迭
		String[] answerArr = new String[] { "0", "0", "0" }; // ����ڰ� �Է��� 3�ڸ� ���� �� �迭
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		String randomNumber = "0";

		// ��ǻ�� ���� �� ����
//		for (int i = 0; i < 3; i++) {
//			// overlapüũ�� ����ؾ� ���� �ѹ� �������� ����
//			{
//				randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); //������ ���� ����
//				System.out.println("������ ���� ���� : " + randomNumber);
//			}while (nb.isOverlap(quizArr, randomNumber, i)); //������ ������ ���ڰ� �ߺ����� üũ
//			System.out.println("�ߺ����� �ʴ� ���� ���� : " + randomNumber);
//		}
		//���� ������� �� ��, ���������� �ߺ��߻� ���� �ݺ� ���� ����.
		
		int i = 0;
		while(i < 3) {
			randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); //������ ���� ����
			System.out.println("������ ���� ���� : " + randomNumber);
			if(nb.isOverlap(quizArr, randomNumber, i)) {
				continue;
			}else {
				i++;
			}
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
		System.out.println(Arrays.toString(scoreArr));
		nb.result(scoreArr);
	}

	// ��ǻ���� ���� ���� ����ڰ� �Է��� ���� ��
	public void compare(String[] quizArr, String[] answerArr, int[] scoreArr) {
		// findStrike(quizArr, answerArr, scoreArr);
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
			if(scoreArr[0] == 3) {
				System.out.println();
			}else {
				System.out.println(scoreArr[0] + "��Ʈ����ũ");
			}
		}
		
		if (scoreArr[1] != 0) {
			System.out.println(scoreArr[1] + "��");
		}
		
		if((scoreArr[0] == 0) && (scoreArr[1] == 0)){
			System.out.println("����");
		}
	}

	/*
	 * num1�� ���� ���� ���Ѱ�, num2�� ���� ���� ���Ѱ�. makeRandomNumber(1, 9)�̸�, 1~9������ ������ ���� ��ȯ
	 */
	public int makeRandomNumber(int num1, int num2) {
		// int randomNumber = 0;
		// {
		// randomNumber = (int)(Math.random() * 10); //���� 10�� ���ϰ� int�� ��ȯ. int�� ���� ��ȯ�ϸ�
		// 0���� ���.
		// System.out.println(randomNumber);
		// }while(!(randomNumber == 0 || randomNumber == 10));
		int randomNumber = (int) (Math.random() * (num2 - num1 + 1)) + num1;
		return randomNumber;
	}

	public boolean isOverlap(String[] quizArr, String randomNumber, int index) {
		System.out.println("�ߺ��߻�0");
		for (int i = 0; i < 3; i++) {
			System.out.println("�ߺ��߻�1");
			if (!(quizArr[i].equals("0")) && (quizArr[i].equals(randomNumber))) {
				System.out.println("�ߺ��߻�2");
				return true;
			}
		}
		insert(quizArr, randomNumber, index); //�ߺ����� �ʴ� ����, quiz�迭�� ����
		System.out.println("not�ߺ��߻�");
		return false;
	}
	
	public void insert(String[] quizArr, String randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
