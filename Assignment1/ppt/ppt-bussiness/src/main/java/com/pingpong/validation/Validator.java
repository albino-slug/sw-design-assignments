package com.pingpong.validation;

public interface Validator<T> {
	
	public int validate(T t);
}