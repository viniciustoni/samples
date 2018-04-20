package com.vinicius.twitter.exceptions;

import static java.text.MessageFormat.format;

public class UserNotFoundException extends Exception
{
    private static final long serialVersionUID = -5617661749345668107L;

    private static final String MESSAGE = "User [{0}] not found.";

    public UserNotFoundException(String userEmail)
    {
        super(format(MESSAGE, userEmail));
    }
}
