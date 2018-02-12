package com.david.hwang;

import java.util.Arrays;
import java.util.Scanner;

public class NumberBaseball {
	public static void main(String[] args) {
		NumberBaseball nb = new NumberBaseball();

		/* 선언 및 초기화 */
		String[] quizArr = new String[] { "0", "0", "0" }; // 컴퓨터가 생성할 임의의 수 3개가 들어갈 배열
		String[] answerArr = new String[] { "0", "0", "0" }; // 사용자가 입력한 3자리 수가 들어갈 배열
		int[] scoreArr = new int[] { 0, 0 }; // [0]:strike, [1]:ball
		String randomNumber = "0";

		// 컴퓨터 랜덤 수 생성
//		for (int i = 0; i < 3; i++) {
//			// overlap체크를 통과해야 다음 넘버 생성으로 진행
//			{
//				randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); //임의의 숫자 생성
//				System.out.println("생성된 랜덤 숫자 : " + randomNumber);
//			}while (nb.isOverlap(quizArr, randomNumber, i)); //생성된 임의의 숫자가 중복인지 체크
//			System.out.println("중복되지 않는 랜덤 숫자 : " + randomNumber);
//		}
		//위의 방식으로 할 때, 간헐적으로 중복발생 무한 반복 현상 보임.
		
		int i = 0;
		while(i < 3) {
			randomNumber = Integer.toString(nb.makeRandomNumber(1, 9)); //임의의 숫자 생성
			System.out.println("생성된 랜덤 숫자 : " + randomNumber);
			if(nb.isOverlap(quizArr, randomNumber, i)) {
				continue;
			}else {
				i++;
			}
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
		System.out.println(Arrays.toString(scoreArr));
		nb.result(scoreArr);
	}

	// 컴퓨터의 랜덤 수와 사용자가 입력한 수를 비교
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
				System.out.println(scoreArr[0] + "스트라이크");
			}
		}
		
		if (scoreArr[1] != 0) {
			System.out.println(scoreArr[1] + "볼");
		}
		
		if((scoreArr[0] == 0) && (scoreArr[1] == 0)){
			System.out.println("낫싱");
		}
	}

	/*
	 * num1은 랜덤 수의 하한값, num2는 랜덤 수의 상한값. makeRandomNumber(1, 9)이면, 1~9까지의 랜덤한 수를 반환
	 */
	public int makeRandomNumber(int num1, int num2) {
		// int randomNumber = 0;
		// {
		// randomNumber = (int)(Math.random() * 10); //먼저 10을 곱하고 int로 변환. int로 먼저 변환하면
		// 0으로 출력.
		// System.out.println(randomNumber);
		// }while(!(randomNumber == 0 || randomNumber == 10));
		int randomNumber = (int) (Math.random() * (num2 - num1 + 1)) + num1;
		return randomNumber;
	}

	public boolean isOverlap(String[] quizArr, String randomNumber, int index) {
		System.out.println("중복발생0");
		for (int i = 0; i < 3; i++) {
			System.out.println("중복발생1");
			if (!(quizArr[i].equals("0")) && (quizArr[i].equals(randomNumber))) {
				System.out.println("중복발생2");
				return true;
			}
		}
		insert(quizArr, randomNumber, index); //중복되지 않는 숫자, quiz배열에 삽입
		System.out.println("not중복발생");
		return false;
	}
	
	public void insert(String[] quizArr, String randomNumber, int index) {
		quizArr[index] = randomNumber;
	}
}
