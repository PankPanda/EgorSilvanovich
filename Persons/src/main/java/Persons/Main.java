package Persons;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main implements Serializable {
    private final String name;
    private final String surname;
    private int age;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Main(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Main(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        return age == main.age && Objects.equals(name, main.name) && Objects.equals(surname, main.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Persons.properties");

        URL resource = Main.class.getClassLoader()
                .getResource("Persons.txt");
        File file = new File(resource.getFile());
        Collection<Main> list = Stream.generate(() -> {
            String name = "Egor" + ((int) (Math.random() * 5));
            String surname = "Silvanovich" + ((int) (Math.random() * 5));
            int age = ((int) (Math.random() * 15 + 15));
            return new Main(name, surname, age);
        })
                .limit(100)
                .filter(person -> person.getAge() < 21)
                .peek(System.out::println)
                .sorted(Comparator.comparing(Main::getName).thenComparing(Main::getSurname))
                .distinct()
                .collect(Collectors.toList());

        System.out.println();
        list.forEach(System.out::println);

        try (ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Main p : list) {
                oop.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Main> list1 = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fileInputStream)) {
            while (fileInputStream.available()>0) {
                Main p = (Main) ois.readObject();
                list1.add(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Main> list2 = new ArrayList<>();
        list1.forEach(p -> list2.add(new Main(p.getName(), p.getSurname())));
        System.out.println("Вывод:");
        for (Main s : list2) {
            System.out.println(resourceBundle.getString("Name") + " " + s.getName() + " " + resourceBundle.getString("Surname") + " " + s.getSurname());
        }
    }

    @Override
    public String toString() {
        String a = String.format("%s ,%s ,%d",name,surname,age);
        return a;
    }
}