package com.crediya.usecase.incrementcreditsummary.exceptions;

import com.crediya.model.exceptions.BusinessException;

public class InvalidAmountException extends BusinessException {
	public InvalidAmountException(String message) {
		super(message);
	}
}
