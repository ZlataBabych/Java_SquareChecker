// Бабич З.А.
// ООП на мові Java
// Лабораторна робота 5
// Кінцевий строк подання: 15 березня 2024
// Використано часу: 1 година
// Це моя власна робота. Не було використано жодної несанкціонованої допомоги.

//Програма зчитує з файлу координати чотирьох точок використовуючи
//відповідний цикл і виводить на екран та у файл інформацію,
//створюють чи ні ці точки квадрат.


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SquareChecker {
    /**
     * Головний метод програми.
     * @param args аргументи командного рядку
     */
    public static void main(String[] args) throws IOException {
        double[][] points = readCoordinatesFromFile("input.txt");

        boolean isSquare = checkIfSquare(points);

        StringBuilder pointsStr = new StringBuilder();

        for (double[] point : points) {
            pointsStr.append(String.format("(%f, %f) ", point[0], point[1]));
        }

        String result = "Точки " + pointsStr + (isSquare ?
                "утворюють" : "не утворюють") + " квадрат.";

        System.out.println(result);

        writeResultToFile("result.txt", result);
    }


    /**
     * Перевіряє, чи утворюють чотири точки квадрат.
     * @param points Масив точок у форматі {{x1, y1}, {x2, y2}, {x3, y3}, {x4, y4}}
     * @return true, якщо точки утворюють квадрат, false - в іншому випадку
     */
    public static boolean checkIfSquare(double[][] points) {
        double[] distances = new double[6];
        int k = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                distances[k++] = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2)
                        + Math.pow(points[i][1] - points[j][1], 2));
            }
        }

        java.util.Arrays.sort(distances); // Сортування масиву довжин

        // Перевірка умови квадрата
        return distances[0] > 0 &&
                distances[0] == distances[1] &&
                distances[1] == distances[2] &&
                distances[2] == distances[3] &&
                distances[4] == distances[5];
    }


    /**
     * Читає координати точок з файлу.
     * @param filename Ім'я файлу для читання
     * @return Масив точок у форматі {{x1, y1}, {x2, y2}, {x3, y3}, {x4, y4}}
     * @throws IOException Виняток у випадку помилки читання з файлу
     */
    public static double[][] readCoordinatesFromFile(String filename)
            throws IOException {
        Scanner scanner = new Scanner(new File(filename));

        double[][] points = new double[4][2];

        for (int i = 0; i < 4; i++) {
            points[i][0] = scanner.nextDouble(); // x координата
            points[i][1] = scanner.nextDouble(); // y координата
        }

        scanner.close();

        return points;
    }


    /**
     * Записує результат у файл.
     * @param filename Ім'я файлу для запису
     * @param result Результат, який потрібно записати у файл
     * @throws IOException Виняток у випадку помилки запису у файл
     */
    public static void writeResultToFile(String filename, String result)
            throws IOException {
        FileWriter writer = new FileWriter(filename, false);
        writer.write(result);
        writer.close(); // Закриття файлу після запису
    }
}
