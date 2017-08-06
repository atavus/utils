package org.atavus.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Iterator;

import lombok.SneakyThrows;

/**
 * Process a file as lines of text
 */
public class Lines implements Iterable<String>, Closeable, Iterator<String> {

    private BufferedReader br;
    private String line;
    private boolean read;

    @SneakyThrows
    public Lines(Path path) {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public Lines(Path path, Charset charset) {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), charset));
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    @SneakyThrows
    public void close() {
        br.close();
    }

    @Override
    @SneakyThrows
    public boolean hasNext() {
        if (!read) {
            line = br.readLine();
            if (line == null) {
                br.close();
            }
            read = true;
        }
        return line != null;
    }

    @Override
    @SneakyThrows
    public String next() {
        if (!read) {
            line = br.readLine();
            if (line == null) {
                br.close();
            }
            return line;
        } else {
            read = false;
            return line;
        }
    }

}
