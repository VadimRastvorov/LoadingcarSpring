package ru.homework.loadingcar.service;

import org.junit.jupiter.api.Test;
import ru.homework.loadingcar.service.fileread.FileReadServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReadServiceImplTest {

    @Test
    void getListFileContent() {
        FileReadServiceImpl fileReadServiceImpl = new FileReadServiceImpl();
        List<String> fileList = fileReadServiceImpl.getListFileContent("package.npk");
        assertTrue(fileList.contains("333"));
    }

    @Test
    void getStringFileContent() {
        FileReadServiceImpl fileReadServiceImpl = new FileReadServiceImpl();
        String file = fileReadServiceImpl.getStringFileContent("package.npk");
        assertEquals("333", file, "не удалось сравнить значение с прочитанного файла");
    }
}