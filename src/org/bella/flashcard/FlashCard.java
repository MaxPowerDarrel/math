package org.bella.flashcard;

import java.util.Random;

public class FlashCard
{
	private int firstNumber;
	private int secondNumber;
	private int operation;

	private static final int MINIMUM_NUMBER = 1;
	private static final int MAXIMUM_NUMBER = 10;

	public FlashCard()
	{
		generateProblem();
	}

	private void generateProblem()
	{
		int a = generateRandomNumber(MINIMUM_NUMBER, MAXIMUM_NUMBER);
		int b = generateRandomNumber(MINIMUM_NUMBER, MAXIMUM_NUMBER);

		operation = generateRandomNumber(1, 4);

		if(isDivision())
		{
			firstNumber = a * b;
			secondNumber = generateRandomNumber(1 ,2) == 1 ? a : b;
		}

		else if(isSubtraction())
		{
			firstNumber = a + b;
			secondNumber = generateRandomNumber(1 ,2) == 1 ? a : b;
		}

		else
		{
			firstNumber = Math.max(a, b);
			secondNumber = Math.min(a, b);
		}
	}

	private int generateRandomNumber(final int lowerBound, final int upperBound)
	{
		Random randomNumber = new Random();
		int range = upperBound - lowerBound + 1;
		int fraction = (int)(range * randomNumber.nextDouble());

		return fraction + 1;
	}

	private boolean isDivision()
	{
		return operation == 4;
	}

	private boolean isSubtraction()
	{
		return operation == 2;
	}

	public int getFirstNumber()
	{
		return firstNumber;
	}

	public int getSecondNumber()
	{
		return secondNumber;
	}

	public int getOperation()
	{
		return operation;
	}

	public int getAnswer()
	{
		switch (operation)
		{
			case (1): return firstNumber + secondNumber;
			case (2): return firstNumber - secondNumber;
			case (3): return firstNumber * secondNumber;
			case (4): return firstNumber / secondNumber;
		}
		return 0;
	}
}
