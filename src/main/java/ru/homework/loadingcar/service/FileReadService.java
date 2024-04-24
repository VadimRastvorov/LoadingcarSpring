package ru.homework.loadingcar.service;

import java.util.List;

public interface FileReadService {
    List<String> getListFileContent(String fileName);
    String getStringFileContent(String fileName);
}
