package chap3;

import chap3.process.Process;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ProcessTest {
    
    @Test
    void processTest1() throws IOException {
        String result = Process.processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("result = " + result);
    }

    @Test
    void processTest2() throws IOException {
        String result = Process.processFile((BufferedReader br) -> br.readLine());
        System.out.println("result = " + result);
    }
}
