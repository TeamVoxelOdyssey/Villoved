package com.guy7cc.villoved.save;

import com.google.gson.JsonObject;

/**
 * Represents a data holder that can load and save data.
 */
public interface DataHolder {
    /**
     * Gets the name of the data holder.
     * This name is used as the key in the json data.
     *
     * @return the name of the data holder
     */
    String getHolderName();

    /**
     * Loads data from a JsonObject.
     *
     * @param data the JsonObject containing the data to load
     */
    void load(JsonObject data);

    /**
     * Saves data to a JsonObject.
     *
     * @return the JsonObject containing the data to save
     */
    JsonObject getWrittenData();
}
