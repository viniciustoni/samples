package com.vinicius.twitter.exceptions;

import static java.text.MessageFormat.format;

import com.vinicius.twitter.model.entity.User;

public class RelationshipAlreadyExistException extends Exception
{
    private static final long serialVersionUID = 2232146613257262384L;

    private static final String MESSAGE = "User [{0}] is already following [{1}].";

    public RelationshipAlreadyExistException(User user, User userFollowing)
    {
        super(format(MESSAGE, user.getEMail(), userFollowing.getEMail()));
    }
}
