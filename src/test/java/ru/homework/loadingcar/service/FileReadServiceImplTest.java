package ru.homework.loadingcar.service;

import org.junit.jupiter.api.Test;
import ru.homework.loadingcar.service.fileread.FileReadServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReadServiceImplTest {

    @Test
    void createListFileContent() {
        FileReadServiceImpl fileReadServiceImpl = new FileReadServiceImpl();
        List<String> fileList = fileReadServiceImpl.createListFileContent("package.npk");
        assertTrue(fileList.contains("333"));
    }

    @Test
    void createStringFileContent() {
        FileReadServiceImpl fileReadServiceImpl = new FileReadServiceImpl();
        String file = fileReadServiceImpl.createStringFileContent("package.npk");
        assertEquals("333", file, "не удалось сравнить значение с прочитанного файла");
    }
}