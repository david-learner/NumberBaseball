package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberBaseball {
	/* 선언 및 초기화 */
	int[] quizArr = { 0, 0, 0 }; // 컴퓨터가 생성할 임의의 수 3개가 들어갈 배열
	int[] answerArr = { 0, 0, 0 }; // 사용자가 입력한 3자리 수가 들어갈 배열
	int[] scoreArr = { 0, 0 }; // [0]:strike, [1]:ball
	int randomNumber = 0;

	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball(); // NumberBaseball 객체 생성

		nb.insertRandomNumber(nb.quizArr, nb.randomNumber); // 컴퓨터 랜덤 수 생성

		while (nb.scoreArr[0] < 3) {
			nb.init(nb.answerArr, nb.scoreArr); // 이전 사용자
			// 첫 번째 매개변수가 별로(?)다. 각각의 리턴값을 변수로 만들어서 넣자니 줄이 늘어난다.
			nb.insertNumber(nb.splitNumber(nb.inputNumber()), nb.answerArr);
			nb.compare(nb.quizArr, nb.answerArr, nb.scoreArr);
			nb.result(nb.scoreArr);
		}
	}

	public int inputNumber() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("숫자를 입력해주세요 ex)123 : ");
		int userInputNumber = scanner.nextInt();
		return userInputNumber;
	}

	public int[] splitNumber(int userInputNumber) {
		int[] userInputNumberArr = { 0, 0, 0 };
		userInputNumberArr[0] = userInputNumber / 100; // 백의자리
		userInputNumberArr[1] = (userInputNumber / 10) % 10; // 십의자리
		userInputNumberArr[2] = userInputNumber % 10; // 일의자리
		return userInputNumberArr;
	}

	public void insertNumber(int[] splittedNumberArr, int[] answerArr) {
		System.arraycopy(splittedNumberArr, 0, answerArr, 0, 3);
	}

	// 이전에 입력된 사용자 입력 수 및 점수 초기화
	public void init(int[] answerArr, int[] scoreArr) {
		Arrays.fill(answerArr, 0);
		Arrays.fill(scoreArr, 0);
	}

	// 컴퓨터의 랜덤 수와 사용자가 입력한 수를 비교
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
		//3스트라이크(정답)를 먼저 체크하고 나머지는 "n스트라이크"로 출력. 반대로 되면 정답대신 3스트라이크 출력.
		if (scoreArr[0] == 3) {
			System.out.print("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
		}else if (scoreArr[0] != 0) {
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

	/*
	 * num1은 랜덤 수의 하한값, num2는 랜덤 수의 상한값. makeRandomNumber(1, 9)이면, 1~9까지의 랜덤한 수를 반환
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
		insert(quizArr, randomNumber, index); // 중복되지 않는 숫자, quiz배열에 삽입
		return false;
	}

	public void insert(int[] quizArr, int randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
