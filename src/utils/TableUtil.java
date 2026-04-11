package utils;

public class TableUtil {

	private static final int WIDTH = 25;

    public static void line(int cols) {
        for (int i = 0; i < cols; i++) {
            System.out.print("+");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    public static void row(String... cols) {
        for (String col : cols) {
            System.out.print("|");
            System.out.printf(" %-"+(WIDTH-2)+"s", col);
        }
        System.out.println("|");
    }

    public static void title(String title) {
        int totalWidth = WIDTH * 2;
        System.out.println("\n" + "=".repeat(totalWidth));
        System.out.printf("%" + ((totalWidth + title.length()) / 2) + "s\n", title);
        System.out.println("=".repeat(totalWidth));
    }
}