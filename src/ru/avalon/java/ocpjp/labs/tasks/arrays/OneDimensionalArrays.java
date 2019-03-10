package ru.avalon.java.ocpjp.labs.tasks.arrays;

import ru.avalon.java.ocpjp.labs.Exercise;
import ru.avalon.java.ocpjp.labs.common.Factory;
import ru.avalon.java.ocpjp.labs.common.ObjectWriter;

import java.io.IOException;
import java.util.*;

/**
 * Задание, направленное на получение умений и навыков
 * объявления и использования одномерных массивов.
 */
public final class OneDimensionalArrays implements Exercise {
    /**
     * Фабрика, создающая одномерный массив случайной
     * длины, содержащие случайные числа в интервале
     * от {@code 0} до {@code 100}.
     *<p>
     * Длина массивов должна колебаться в диапазоне от
     * 10, до 20 элементов.
     */
    private Factory<int[]> factory;

    /**
     * Выполняет сортировку одномерного массива по
     * возрастанию.
     */
    private Sort<int[]> sort;

    /**
     * Объект, отвечающий за вывод одномерного массива в
     * консоль.
     * <p>
     * Массив следет выводить в одну строку, разделяя
     * элементы массива запятыми и пробелами. Массив должен
     * быть заключён в квадратные скобки.
     * <p>
     * Например: {@code [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}
     */
    private ObjectWriter<int[]> writer;

    private int[] formationArrayList() {
        int[] temp = new int[10 + (int)(Math.random() * 10)];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = (int)(Math.random() * 100);
        }

        return temp;
    }


    public OneDimensionalArrays() {
        this.factory = this::formationArrayList;

        this.writer = new ObjectWriter<int[]>() {
            @Override
            public void write(int[] object) throws IOException {
                for (int i : object) {
                    System.out.println(i);
                }
            }

            @Override
            public void close() throws IOException {
                //Nothing.
            }
        };

        this.sort = Arrays::sort;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws IOException {
        int[] array = factory.create();
        sort.run(array);
        writer.write(array);
    }
}

