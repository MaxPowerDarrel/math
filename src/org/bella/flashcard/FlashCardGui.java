package org.bella.flashcard;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlashCardGui extends Application
{
	private static final String TITLE = "Bella's Flash Cards";
	private static final String FLASH_CARD_FXML = "flashCard.fxml";
	private static final String ICON = "math.png";
	private static final String ADD = "+";
	private static final String SUBTRACT = "-";
	private static final String MULTIPLY = "X";
	private static final String DIVIDE = "÷";
	private static final String POINTS = "Points: ";

	FlashCard flashCard;
	private int points;

	@FXML
	private Label firstNumber;
	@FXML
	private Label secondNumber;
	@FXML
	private Label operationLabel;
	@FXML
	private Label pointsLabel;
	@FXML
	private TextField answerField;
	@FXML
	private Button nextButton;

	public static void main( String[] args )
	{
		launch(args);
	}

	@Override
	public void start( Stage primaryStage ) throws Exception
	{
		primaryStage.setTitle(TITLE);
		primaryStage.getIcons().add(new Image(FlashCard.class.getResourceAsStream(ICON)));
		Pane mainPain = initFlashCard();
		assert mainPain != null;
		Scene mainScene = new Scene(mainPain);
		primaryStage.setScene(mainScene);
		primaryStage.show();

		generateFlashCard();
	}

	private Pane initFlashCard()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FLASH_CARD_FXML));
		loader.setController(this);

		try
		{
			Pane mainPane = loader.load();
			nextButton.setOnKeyPressed(event -> {
				if (event.getCode().equals(KeyCode.ENTER))
					generateFlashCard();
			});

			return mainPane;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@FXML
	private void generateFlashCard()
	{
		flashCard = new FlashCard();
		pointsLabel.setText(POINTS + points);
		firstNumber.setText(Integer.toString(flashCard.getFirstNumber()));
		secondNumber.setText(Integer.toString(flashCard.getSecondNumber()));
		answerField.setText("");
		answerField.setStyle("-fx-background-color: white;");

		switch (flashCard.getOperation())
		{
			case (1):
				operationLabel.setText(ADD);
				break;
			case (2):
				operationLabel.setText(SUBTRACT);
				break;
			case (3):
				operationLabel.setText(MULTIPLY);
				break;
			case (4):
				operationLabel.setText(DIVIDE);
		}

		nextButton.setDisable(true);
	}

	@FXML
	private void validateAnswer()
	{
		if (Integer.parseInt(answerField.getText()) == flashCard.getAnswer())
		{
			answerField.setStyle("-fx-background-color: green;");
			points++;
			pointsLabel.setText(POINTS + points);
			nextButton.setDisable(false);
			nextButton.requestFocus();
		}

		else
		{
			answerField.setStyle("-fx-background-color: red;");
			points--;
			pointsLabel.setText(POINTS + points);
		}
	}
}
