package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* 선언 및 초기화 */
		String[] quizArr = new String[] { "0", "0", "0" }; // 컴퓨터가 생성할 임의의 수 3개가 들어갈 배열
		String[] answerArr = new String[] { "0", "0", "0" }; // 사용자가 입력한 3자리 수가 들어갈 배열
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		String randomNumber = "0";

		// 컴퓨터 랜덤 수 생성
		int i = 0;
		while (i < 3) {
			randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); // 임의의 숫자 생성
			if (nb.isOverlap(quizArr, randomNumber, i)) {
				continue;
			} else {
				i++;
			}
		}

		while (scoreArr[0] < 3) {
			nb.init(answerArr, scoreArr);
			// 사용자 입력 처리
			Scanner scanner = new Scanner(System.in);
			System.out.print("숫자를 입력해주세요 ex)123 : ");
			String userInputNum = scanner.next();

			// 사용자가 입력한 수(문자열)를 배열에 하나씩 쪼개어 넣는다.
			answerArr = userInputNum.split("(?!^)");
			// 컴퓨터와 사용자의 수 비교
			nb.compare(quizArr, answerArr, scoreArr);
			nb.result(scoreArr);
		}
	}

	// 사용자 입력 값이 담긴 배열과 점수 배열을 초기화
	public void init(String[] answerArr, int[] scoreArr) {
		Arrays.fill(answerArr, "0");
		Arrays.fill(scoreArr, 0);
	}

	// 컴퓨터의 랜덤 수와 사용자가 입력한 수를 비교
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
				System.out.print("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
			} else {
				System.out.print(scoreArr[0] + "스트라이크 ");
			}
		}

		if (scoreArr[1] != 0) {
			System.out.println(scoreArr[1] + "볼");
		}

		if ((scoreArr[0] == 0) && (scoreArr[1] == 0)) {
			System.out.println("낫싱");
		}
	}

	/*
	 * num1은 랜덤 수의 하한값, num2는 랜덤 수의 상한값. makeRandomNumber(1, 9)이면, 1~9까지의 랜덤한 수를 반환
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
		insert(quizArr, randomNumber, index); // 중복되지 않는 숫자, quiz배열에 삽입
		return false;
	}

	public void insert(String[] quizArr, String randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
