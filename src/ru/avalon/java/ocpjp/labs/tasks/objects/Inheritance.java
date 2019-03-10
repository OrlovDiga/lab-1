package ru.avalon.java.ocpjp.labs.tasks.objects;

import ru.avalon.java.ocpjp.labs.Exercise;
import ru.avalon.java.ocpjp.labs.common.ObjectWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Задание, направленное на изучение следующих тем:
 * 1. наследование;
 * 2. полиморфизм;
 * 3. использование коллекции ArrayList
 */
public final class Inheritance implements Exercise {

    /**
     * Объект, выполняющий сравнение объектов типа
     * {@link Citizen}.
     */
    private Comparator<Citizen> comparator;

    /**
     * Источник случайного набора экземпляров класса
     * {@link Citizen}.
     * <p>
     * Объектов в источнике должно быть случайное количечство
     * в диапазоне от {@code 20} до {@code 30}.
     * <p>
     * Формировать экземпляры следует с использованием данных
     * словарей, расположенных в пакете
     * {@link ru.avalon.java.ocpjp.labs.resources}.
     */
    private Iterable<Citizen> source;

    /**
     * Объект, отвечающий за вывод экземпляров {@link Citizen}
     * в консоль.
     */
    private ObjectWriter<Citizen> writer;


    private List<String> getValues(String fileName, int quanityLine) {
        List<String> temp = new ArrayList<>();

        try {
            temp = Files.readAllLines(Paths.get(fileName));
            Collections.shuffle(temp);
            temp = temp.subList(0, quanityLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }


    public Inheritance() {
        int count = 20 + (int)(Math.random() * 10);
        List<String> countries =  getValues("/Users/macbook/Desktop/Lab1JavaGitHub/src/ru/avalon/java/ocpjp/labs/resources/countries.txt", count);
        List<String> names = getValues("/Users/macbook/Desktop/Lab1JavaGitHub/src/ru/avalon/java/ocpjp/labs/resources/first-names.txt", count);
        List<String> surnames = getValues("/Users/macbook/Desktop/Lab1JavaGitHub/src/ru/avalon/java/ocpjp/labs/resources/last-names.txt", count);
        List<Citizen> persons = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            persons.add(new CitizenImpl(surnames.get(i).toString(),
                    names.get(i).toString(),
                    new CountryImpl(countries.get(i).toString())));
        }

        this.source = persons;

        this.writer = new ObjectWriter<Citizen>() {
            @Override
            public void write(Citizen object) {
                if (object instanceof CitizenImpl) {

                    CitizenImpl temp = (CitizenImpl) object;
                    CountryImpl coun = (CountryImpl) temp.getCountry();

                    System.out.println(coun.getCode() + " " +
                            temp.getName() + " " +
                            temp.getLastName() + " " +
                            coun.getName());
                }
            }

            @Override
            public void close() {
                //Nothing.
            }
        };

        this.comparator = Comparator.comparing(obj -> obj.getCountry().getName());
        this.comparator = comparator.thenComparing((obj -> obj.getName()));
        this.comparator = comparator.thenComparing((obj -> obj.getLastName()));
    }


    public Inheritance(Comparator<Citizen> comparator, Iterable<Citizen> source, ObjectWriter<Citizen> writer) {
        this.comparator = comparator;
        this.source = source;
        this.writer = writer;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws IOException {
        List<Citizen> citizens = new ArrayList<>();

        for (Citizen aSource : source) {
            citizens.add(aSource);
        }

        citizens.sort(comparator);

        for (Citizen citizen : citizens) {
            writer.write(citizen);
        }
    }
}
