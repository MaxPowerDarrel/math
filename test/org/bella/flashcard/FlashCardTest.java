package org.bella.flashcard;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class FlashCardTest
{
	@Test
	public void testProblem()
	{
		FlashCard flashcard = new FlashCard();

		int firstNumber = flashcard.getFirstNumber();
		int secondNumber = flashcard.getSecondNumber();
		int operation = flashcard.getOperation();

		assertEquals(true, firstNumber >= secondNumber);
		assertEquals(true, secondNumber <= 10);
		assertEquals(true, firstNumber > 0 && secondNumber > 0);
		assertEquals(true, operation >= 1 && operation <= 4);

		switch (operation)
		{
			case(1):
				assertEquals(flashcard.getAnswer(), firstNumber + secondNumber);
				break;
			case(2):
				assertEquals(flashcard.getAnswer(), firstNumber - secondNumber);
				break;
			case(3):
				assertEquals(flashcard.getAnswer(), firstNumber * secondNumber);
				break;
			case(4):
				assertEquals(flashcard.getAnswer(), firstNumber / secondNumber);
		}
	}

	@Test
	public void repeatedTest()
	{
		for (int i = 0; i < 1000000; i++)
			testProblem();
	}
}