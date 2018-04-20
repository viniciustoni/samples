package com.vinicius.twitter.dto;

import java.io.Serializable;

public class ValidationExceptionDTO implements Serializable
{
    private static final long serialVersionUID = -5560557177158250422L;

    private final String fieldName;
    private final String fieldMessage;

    public ValidationExceptionDTO(String fieldName, String fieldMessage)
    {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getFieldMessage()
    {
        return fieldMessage;
    }
}
