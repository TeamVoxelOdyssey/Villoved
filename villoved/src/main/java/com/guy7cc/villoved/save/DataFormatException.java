package com.guy7cc.villoved.save;

/**
 * Exception thrown when there is a data format error.
 * This can occur when loading data from JSON or other formats.
 */
public class DataFormatException extends Exception {
    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(String objName, Exception base) {
        super(String.format("An error has occurred while loading %s from json", objName), base);
    }

    public DataFormatException(Class<?> clazz, Exception base) {
        this(clazz.getName(), base);
    }
}
