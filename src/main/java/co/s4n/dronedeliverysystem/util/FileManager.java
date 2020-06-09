package co.s4n.dronedeliverysystem.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    private final static Logger LOGGER = Logger.getLogger(FileManager.class);

    public static List<String> readLinesFromFile(final String filePath) {
        final List<String> lines = new ArrayList<>();
        try {
            final File file = new File(filePath);
            final Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                final String data = myReader.nextLine();
                lines.add(data);
            }
            myReader.close();
        } catch (final FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return lines;
    }

    public static List<String> getFilesFromFolder(final String folder) {
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {
            return walk.map(Path::toString)
                    .filter(file -> file.endsWith(".txt")).collect(Collectors.toList());
        } catch (final IOException e) {
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void cleanUpFolder(final String folder) {
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {
            walk.map(Path::toFile)
                    .filter(file -> !file.isDirectory())
                    .forEach(File::delete);
        } catch (final IOException e) {
            LOGGER.error(e.getMessage());
        }


    }

    public static void writeFiles(final String value, final String filePath) {
        try (final FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(value);
        } catch (final IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
