package com.homework.packagedelivery.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This enum serves for commands, which the user can enter.
 */
@RequiredArgsConstructor
@Getter
public enum UserInputEnum {

    QUIT_APP("quit"); // when user enters this input/command, app will quit.

    private final String consoleInput;
}
