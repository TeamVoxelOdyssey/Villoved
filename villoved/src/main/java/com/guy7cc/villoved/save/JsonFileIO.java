package com.guy7cc.villoved.save;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class for reading and writing JSON files.
 * It uses Gson for JSON parsing and serialization.
 */
public class JsonFileIO {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private final Logger logger;

    public JsonFileIO(Logger logger){
        this.logger = logger;
    }

    /**
     * Saves a JsonElement to a file.
     *
     * @param element the JsonElement to save
     * @param file the file to save to
     */
    public void save(@NotNull JsonElement element, @NotNull File file) {
        try {
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(gson.toJson(element));
            writer.close();
        } catch (IOException exception) {
            logger.log(
                    Level.SEVERE,
                    String.format("Could not save %s due to I/O errors. ", file),
                    exception
            );
        } catch (SecurityException exception) {
            logger.log(
                    Level.SEVERE,
                    String.format("Could not save %s due to security problems. ", file),
                    exception
            );
        }
    }

    /**
     * Loads a JsonElement from a file.
     *
     * @param file the file to load from
     * @return the loaded JsonElement, or null if the file could not be read
     */
    @Nullable
    public JsonElement load(@NotNull File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return JsonParser.parseString(sb.toString());
        } catch (FileNotFoundException exception) {
            return null;
        } catch (IOException exception) {
            logger.log(
                    Level.SEVERE,
                    String.format("Could not read %s due to I/O errors.", file),
                    exception
            );
        } catch (JsonParseException exception) {
            logger.log(
                    Level.SEVERE,
                    String.format("Could not read %s because the file format was invalid.", file),
                    exception
            );
        } catch (ClassCastException exception) {
            logger.log(
                    Level.SEVERE,
                    String.format("Could not read %s because the element was not JsonObject", file),
                    exception
            );
        }
        return null;
    }
}
