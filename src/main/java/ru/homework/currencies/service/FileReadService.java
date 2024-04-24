package ru.homework.currencies.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class FileReadService {
    private static final String FILE_DIRECTORY_RESOURCES = "tsv";

    @SneakyThrows
    public List<String> getListFileContent(String fileName) {
        return Files.readAllLines(getFullPath(FILE_DIRECTORY_RESOURCES.concat("/".concat(fileName))), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private Path getFullPath(String fileName) {
        return Paths.get(Objects.requireNonNull(this.getClass()
                        .getClassLoader()
                        .getResource(fileName))
                .toURI());
    }
}