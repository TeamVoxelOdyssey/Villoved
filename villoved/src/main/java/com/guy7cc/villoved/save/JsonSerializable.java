package com.guy7cc.villoved.save;

import com.google.gson.JsonElement;

/**
 * Interface for classes that can be serialized to and from JSON.
 *
 * @param <T> the type of the object being serialized
 */
public interface JsonSerializable<T> {
    /**
     * Initializes the object.
     *
     * @return the initialized object
     */
    T initialize();

    /**
     * Converts the object to a JSON element.
     *
     * @return the JSON element representing the object
     */
    JsonElement toJson();

    /**
     * Converts a JSON element to an object.
     *
     * @param j the JSON element to convert
     * @return the object represented by the JSON element
     * @throws DataFormatException if the JSON element cannot be converted to an object
     */
    T fromJson(JsonElement j) throws DataFormatException;
}
