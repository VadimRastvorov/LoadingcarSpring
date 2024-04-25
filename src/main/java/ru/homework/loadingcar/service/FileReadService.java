package ru.homework.loadingcar.service;

import java.util.List;

public interface FileReadService {
    List<String> createListFileContent(String fileName);
    String createStringFileContent(String fileName);
}
