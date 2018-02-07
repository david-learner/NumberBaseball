package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* 선언 및 초기화 */
		String[] quizArr = new String[] { "0", "0", "0" }; // 컴퓨터가 생성할 임의의 수 3개가 들어갈 배열
		String[] answerArr = new String[] { "0", "0", "0" }; // 사용자가 응답한 3자리 수가 들어갈 배열
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		int strike = 0; // strike 개수
		int ball = 0; // ball 개수
		// int nothing = 0; // nothing 개수

		// 컴퓨터 랜덤 수 생성
		for (int i = 0; i < 3; i++) {
			quizArr[i] = nb.makeRandomNumber(1, 9);
		}
		System.out.println("컴퓨터 랜덤 수 : " + Arrays.toString(quizArr));

		// 사용자 입력 처리
		Scanner scanner = new Scanner(System.in);
		System.out.println("숫자를 입력해주세요 ex)123 : ");
		String userInputNum = scanner.next();
		System.out.println(userInputNum);

		// 사용자가 입력한 수(문자열)를 배열에 하나씩 쪼개어 넣는다.
		answerArr = userInputNum.split("(?!^)");
		System.out.println("사용자가 입력한 수 : " + Arrays.toString(answerArr));
		// System.out.println(nb.makeRandomNumber(1,9));

		// 컴퓨터와 사용자의 수 비교
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

	// 컴퓨터의 랜덤 수와 사용자가 입력한 수를 비교
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
	 * num1은 랜덤 수의 하한값, num2는 랜덤 수의 상한값. makeRandomNumber(1, 9)이면, 1~9까지의 랜덤한 수를 반환
	 */
	public String makeRandomNumber(int num1, int num2) {
		// int randomNumber = 0;
		// {
		// randomNumber = (int)(Math.random() * 10); //먼저 10을 곱하고 int로 변환. int로 먼저 변환하면
		// 0으로 출력.
		// System.out.println(randomNumber);
		// }while(!(randomNumber == 0 || randomNumber == 10));
		int randomNumber = (int) (Math.random() * (num2 - num1 + 1)) + num1;
		return Integer.toString(randomNumber);
	}
}
