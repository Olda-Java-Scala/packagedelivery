package com.homework.packagedelivery.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * All texts, which this app writes into console, are saved here.
 */
@Getter
@RequiredArgsConstructor
public enum ConsoleOutputEnum {

    WELCOME("Welcome to delivery system."),
    ENTER_TEXT("Please enter valid line of package data, then hit enter button. You can continue by entering next line of package data. If you want to quit the app, just write 'quit' and again hit enter button."),
    LINE_FORMAT_INSTRUCTIONS("Valid format: <weight: positive number, max weight: 10kg, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>"),
    LINE_EXAMPLE("example: '3.4 08801'"),
    QUIT_APP_TEXT("Delivery system is quiting, have a nice day."),
    INVALID_LINE_TEXT("Entered line is invalid, please enter again valid line."),
    OUTPUT_FROM_STORAGE("Currently, these deliveries are stored :");

    private final String consoleOutput;
}
