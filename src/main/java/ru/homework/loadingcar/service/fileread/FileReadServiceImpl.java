package ru.homework.loadingcar.service.fileread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.loadingcar.service.FileReadService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FileReadServiceImpl implements FileReadService {
    private static final String FILE_DIRECTORY_RESOURCES = "packages";

    @SneakyThrows
    private Path getFullPath(String fileName) {
        return Paths.get(Objects.requireNonNull(Objects.requireNonNull(this.getClass()
                        .getClassLoader()
                        .getResource(FILE_DIRECTORY_RESOURCES.concat("/".concat(fileName))))
                .toURI()));
    }

    @SneakyThrows
    public List<String> createListFileContent(String fileName) {
        return Files.readAllLines(getFullPath(fileName), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public String createStringFileContent(String fileName) {
        return Files.readString(getFullPath(fileName), StandardCharsets.UTF_8);
    }
}
