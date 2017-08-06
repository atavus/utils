package org.atavus.utils;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class LinesTest {

    @Test
    public void testLinesClose() {
        Lines lines = new Lines(Paths.get("src/test/resources/lines.txt"));
        lines.close();
    }

    @Test
    public void testLinesNext() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        Lines lines = new Lines(Paths.get("src/test/resources/lines.txt"));
        for (String line = lines.next(); line != null; line = lines.next()) {
            assertEquals(answer.remove(0), line);
        }
        lines.close();
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesForEach() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        try (Lines lines = new Lines(Paths.get("src/test/resources/lines.txt"));) {
            lines.forEach((s) -> {
                assertEquals(answer.remove(0), s);
            });
        }
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesCharset() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        try (Lines lines = new Lines(Paths.get("src/test/resources/lines.txt"), StandardCharsets.US_ASCII);) {
            lines.forEach((s) -> {
                assertEquals(answer.remove(0), s);
            });
        }
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesIterable() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        for (String line : new Lines(Paths.get("src/test/resources/lines.txt"))) {
            assertEquals(answer.remove(0), line);
        }
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesIterator() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        Iterator<String> it = new Lines(Paths.get("src/test/resources/lines.txt"));
        while (it.hasNext()) {
            assertEquals(answer.remove(0), it.next());
        }
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesIterator2() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        Iterator<String> it = new Lines(Paths.get("src/test/resources/lines.txt"));
        for (String line = it.next(); line != null; line = it.next()) {
            assertEquals(answer.remove(0), line);
        }
        assertEquals(0, answer.size());
    }

    @Test
    public void testLinesIterableTooMany() {
        List<String> answer = new LinkedList<>();
        answer.add("One");
        answer.add("Two");
        answer.add("Three");
        answer.add("Four");
        for (String line : new Lines(Paths.get("src/test/resources/lines.txt"))) {
            assertEquals(answer.remove(0), line);
        }
        assertEquals("Four", answer.remove(0));
    }

}
