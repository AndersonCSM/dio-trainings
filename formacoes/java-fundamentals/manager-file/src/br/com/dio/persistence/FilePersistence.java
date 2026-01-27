package br.com.dio.persistence;

import java.io.FileNotFoundException;

public interface FilePersistence {

    String write(final String data);

    boolean remove(final String sentence);

    String replace(final String oldContent, final String newContent);

    String findAll() throws FileNotFoundException;

    String findBy(final String sentence);
}
