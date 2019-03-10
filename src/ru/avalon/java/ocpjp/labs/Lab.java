package ru.avalon.java.ocpjp.labs;

import ru.avalon.java.ocpjp.labs.tasks.arrays.OneDimensionalArrays;
import ru.avalon.java.ocpjp.labs.tasks.arrays.TwoDimensionalArrays;
import ru.avalon.java.ocpjp.labs.tasks.objects.Inheritance;

import java.io.IOException;

public class Lab {

    /**
     * Точка входа в приложение.
     * <p>
     * При входе в приложение, выполняется создание получение
     * экземпляра задания с использованием фабричного метода
     * {@code create} интерфейса {@link Exercise}.
     *
     * @param args аргументы командной строки
     */
    public static void main(String ... args) throws IOException {
        Exercise exercise = Exercise.create(args);
        try {
            exercise.run();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        Inheritance k = new Inheritance();
        k.run();



        /*
         * TODO(Студент): Пользуясь терминалом, запустить все задания лабораторной работы
         * 1. Запустить задание OneDimensionalArrays
         * 2. Запустить задание TwoDimensionalArrays
         * 3. Запустить задание Inheritance
         */
    }
}
