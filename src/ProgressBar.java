public class ProgressBar {
    static void progressBar(int all, int now) {
        final int MAX_PIPE_CHAR = 10;
        float num = now * MAX_PIPE_CHAR * 1.01f; // 1.01f to account for any round off
        int current = (int) (num / all);
        int rest = MAX_PIPE_CHAR - current;

        System.out.print("\r[");
        for (int a = 1; a <= current; a++) {
            System.out.print("|");
        }
        for (int b = 1; b <= rest; b++) {
            System.out.print(" ");
        }
        System.out.print("]");

    }
}
