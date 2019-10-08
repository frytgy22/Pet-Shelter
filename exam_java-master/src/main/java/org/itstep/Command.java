package org.itstep;

import java.io.File;
import java.io.IOException;

public abstract class Command {
    // TODO: В классе 6 метдоов, но используется только один!
    // Лучше было бы определить один метод - execute, который принимает переменное
    // количество аргументов и возвращает результат своей работы
    // Все команды надо было вынести в отдельный пакет
    /**
     * Метод выводит список файлов в текущей директории
     *
     * @param file Файл(содержит путь текущей директории)
     */
    public void dir(File file) {
    }

    /**
     * Метод,чтоб перейти в директорию, путь к которой задан первым аргументом
     *
     * @param wayDirectory Строка, содержащая путь директории для перехода в нее
     * @param existFile    Файл(содержит путь текущей директории)
     * @return File
     */
    public File cd(String wayDirectory, File existFile) {
        return null;
    }

    /**
     * Метод, чтоб вывести полный путь до текущей директории
     *
     * @param file Файл(содержит путь текущей директории)
     */
    public void pwd(File file) {
    }

    /**
     * Метод выводит содержимое текстового файла
     *
     * @param fileName имя файла для чтения
     * @throws IOException исключение, если файл не найден
     */
    public void cat(String fileName) throws IOException {
    }

    /**
     * Метод загружает файл
     *
     * @param url           ссылка для скачивания файла
     * @param fileDirectory куда сохранить файл
     */
    public void download(String url, File fileDirectory) {
    }

    /**
     * Метод выполняет поиск файла в файловой системе
     *
     * @param filenameForSearch имя файла для поиска
     */
    public void find(String filenameForSearch) {
    }
}
