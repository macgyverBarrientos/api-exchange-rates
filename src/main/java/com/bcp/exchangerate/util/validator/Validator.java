package com.bcp.exchangerate.util.validator;

public interface Validator {
    <T> void validate(T var1, Class<?>... var2);
}
