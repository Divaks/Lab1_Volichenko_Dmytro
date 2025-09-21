class LucasNumber {
    private int index;
    private long value;

    public LucasNumber(int index, long value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public long getValue() {
        return value;
    }

    public boolean isCube() {
        long root = Math.round(Math.cbrt(value));
        return root * root * root == value;
    }

    @Override
    public String toString() {
        return "L(" + index + ") = " + value;
    }
}

public class LucasNumbersProgram {
    public static LucasNumber[] generateLucasNumbers(int n) {
        LucasNumber[] numbers = new LucasNumber[n];

        long a = 2; // L(0)
        long b = 1; // L(1)

        if (n > 0) numbers[0] = new LucasNumber(0, a);
        if (n > 1) numbers[1] = new LucasNumber(1, b);

        for (int i = 2; i < n; i++) {
            long c = a + b;
            numbers[i] = new LucasNumber(i, c);
            a = b;
            b = c;
        }
        return numbers;
    }

    public static void main(String[] args) {
        int N = -1;

        if (args.length > 0) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: аргумент має бути цілим числом!");
                System.exit(1);
            }
        } else {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            boolean valid = false;
            while (!valid) {
                System.out.print("Введіть N (додатне ціле число): ");
                if (sc.hasNextInt()) {
                    N = sc.nextInt();
                    if (N > 0) {
                        valid = true;
                    } else {
                        System.out.println("Помилка: N повинно бути більше за 0!");
                    }
                } else {
                    System.out.println("Помилка: введене значення не є цілим числом!");
                    sc.next(); // очистка буфера
                }
            }
        }

        if (N <= 0) {
            System.out.println("Помилка: N повинно бути більше за 0!");
            return;
        }

        LucasNumber[] numbers = generateLucasNumbers(N);

        System.out.println("Перші " + N + " чисел Люка:");
        for (LucasNumber num : numbers) {
            System.out.println(num + (num.isCube() ? "  <-- куб!" : ""));
        }
    }
}