package chap3.process;

import chap3.process.BufferReaderProcessor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Process {

    private static final String ADDRESS = ".\\example.txt";

    public static String processFile(BufferReaderProcessor b) throws IOException {
        try (BufferedReader br =
            new BufferedReader(new FileReader(ADDRESS))) {
            return b.process(br);
        }
    }
}
