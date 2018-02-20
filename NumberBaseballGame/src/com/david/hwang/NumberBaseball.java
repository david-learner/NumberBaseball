package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	public static void main(String[] args) {
		/* 선언 및 초기화 */
		int[] quizArr = { 0, 0, 0 }; // 컴퓨터가 생성할 임의의 수 3개가 들어갈 배열
		int[] answerArr = { 0, 0, 0 }; // 사용자가 입력한 3자리 수가 들어갈 배열
		int[] scoreArr = { 0, 0 }; // [0]:strike, [1]:ball

		// NumberBaseball nb = new NumberBaseball(); // 인스턴스를 사용하지 않고 구현
		quizArr = makeRandomNumberArr(); // 컴퓨터 랜덤 수 배열 생성

		while (scoreArr[0] < 3) {
			init(answerArr); // 이전 사용자의 입력값 초기화
			answerArr = splitNumber(inputNumber());
			scoreArr = compare(quizArr, answerArr);
			result(scoreArr);
		}
	}

	public static int inputNumber() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("숫자를 입력해주세요 ex)123 : ");
		int userInputNumber = scanner.nextInt();
		//scanner.close();
		return userInputNumber;
	}

	public static int[] splitNumber(int userInputNumber) {
		int[] userInputNumberArr = { 0, 0, 0 };
		userInputNumberArr[0] = userInputNumber / 100; // 백의자리
		userInputNumberArr[1] = (userInputNumber / 10) % 10; // 십의자리
		userInputNumberArr[2] = userInputNumber % 10; // 일의자리
		return userInputNumberArr;
	}

	// 이전에 입력된 사용자 입력 수 및 점수 초기화
	public static void init(int[] initiatedArr) {
		Arrays.fill(initiatedArr, 0);
	}

	// 컴퓨터의 랜덤 수와 사용자가 입력한 수를 비교
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
		// 3스트라이크(정답)를 먼저 체크하고 나머지는 "n스트라이크"로 출력. 반대로 되면 정답대신 3스트라이크 출력.
		if (scoreArr[0] == 3) {
			System.out.print("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
		} else if (scoreArr[0] != 0) {
			System.out.print(scoreArr[0] + "스트라이크 ");
		}

		if (scoreArr[1] != 0) {
			System.out.print(scoreArr[1] + "볼");
		}

		if ((scoreArr[0] == 0) && (scoreArr[1] == 0)) {
			System.out.print("낫싱");
		}

		System.out.println("");
	}


	// num1은 랜덤 수의 하한값, num2는 랜덤 수의 상한값. makeRandomNumber(1, 9)이면, 1~9까지의 랜덤한 수를 반환
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
		insert(quizArr, randomNumber, index); // 중복되지 않는 숫자, quiz배열에 삽입
		return false;
	}

	public static void insert(int[] quizArr, int randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
