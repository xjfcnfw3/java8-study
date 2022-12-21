package chap3.process;

public class RunnableRamda {


    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello World");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
    }
}
