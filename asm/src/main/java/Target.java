public class Target {

    public static void main(String[] args) {
        printf();
    }

    private int a;
    private int b;

    private int get(int a) {
        return a;
    }

    private void set() {
    }

    public static void printf() {
        System.out.println("Target.printf");
        int a = 1 / 0;
    }
}
