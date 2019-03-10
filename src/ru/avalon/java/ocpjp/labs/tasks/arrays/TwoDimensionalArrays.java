package ru.avalon.java.ocpjp.labs.tasks.arrays;

import ru.avalon.java.ocpjp.labs.Exercise;
import ru.avalon.java.ocpjp.labs.common.Factory;
import ru.avalon.java.ocpjp.labs.common.ObjectWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Задание, направленное на получение умений и навыков
 * объявления и использования многомерных массивов.
 */
public final class TwoDimensionalArrays implements Exercise {
    /**
     * Фабрика, создающая двумерный массив, содержащий
     * случайные числа в диапазоне от {@code 0} до
     * {@code 100}.
     * <p>
     * Количество строк и столбцов должно быть случайным
     * и колебатся в диапазоне от {@code 10} до {@code 20}.
     * <p>
     * Массив должен быть прямоугольным. То есть
     * количество элементов всех строк должно совпадать,
     * как и количество элементов всех столбцов.
     */
    private Factory<int[][]> factory;

    /**
     * Выполняет соритровку двумерного массива таким
     * образом, что все элементы массива должны быть
     * расположены по возрастанию слева направо, сверху
     * вниз.
     * <p>
     * Например:
     *  1  2  3  4  5
     *  6  7  8  9 10
     * 11 12 13 14 15
     * 16 17 18 19 20
     */
    private Sort<int[][]> sort;

    /**
     * Объект, отвечающий за вывод в консоль двумерного
     * массива.
     * <p>
     * Массив, должен выводиться построчно.
     */
    private ObjectWriter<int[][]> writer;


    private int[][] getTwoArray() {
        int[][] temp = new int[10 + (int) (Math.random() * 10)][10 + (int) (Math.random() * 10)];

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                temp[i][j] = (int) (Math.random() * 100);
            }
        }
        return temp;
    }


    public TwoDimensionalArrays() {
        this.factory = new Factory<int[][]>() {
            @Override
            public int[][] create() {
                return getTwoArray();
            }
        };

        this.sort = new Sort<int[][]>() {
            @Override
            public void run(int[][] dataSet) {
                int[] arrTemp = new int[dataSet.length * dataSet[0].length];
                int indexTemp = 0;

                for (int i = 0; i < dataSet.length; i++) {
                    for (int j = 0; j < dataSet[0].length; j++) {
                        arrTemp[indexTemp++] = dataSet[i][j];
                    }
                }

                for (int i = 0; i < arrTemp.length; i++) {
                    for (int j = i; j < arrTemp.length; j++) {
                        if (arrTemp[i] > arrTemp[j]) {
                            arrTemp[i] ^= (arrTemp[j] ^= arrTemp[i]);
                            arrTemp[j] ^= arrTemp[i];
                        }
                    }
                }

                indexTemp = 0;
                for (int i = 0; i < dataSet.length; i++) {
                    for (int j = 0; j < dataSet[0].length; j++) {
                        dataSet[i][j] = arrTemp[indexTemp++];
                    }
                }
            }
        };

        this.writer = new ObjectWriter<int[][]>() {
            @Override
            public void write(int[][] object) throws IOException {
                for (int i = 0; i < object.length; i++) {
                    System.out.println();
                    for(int j = 0; j < object[i].length; j++) {
                        System.out.print(object[i][j] + " ");
                    }
                }
            }
            @Override
            public void close() throws IOException {
                //Nothing
            }
        };
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws IOException {
        int[][] array = factory.create();
        sort.run(array);
        writer.write(array);
    }
}
