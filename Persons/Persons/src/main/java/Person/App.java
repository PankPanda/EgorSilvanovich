package Person;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {

        List<Person> persons = MethodsForPerson.personList(15, 30);

        List<Person> personsWithAge = persons.stream().filter(x -> (x.getAge() <= 21)).collect(Collectors.toList());

        for (Person person : personsWithAge) {
            System.out.println(person.toString());
        }

        List<Person> sortPersons = personsWithAge.stream()
                .sorted(Comparator.comparing(Person::getSurName).thenComparing(Person::getName))
                .collect(Collectors.toList());

        List<Person> notDoublePerson = MethodsForPerson.withoutDuble(sortPersons);
        List<Person> deleteDouble = sortPersons.stream().distinct().collect(Collectors.toList());

        FileOutputStream fos = new FileOutputStream("Task4-fileText.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(notDoublePerson);
        oos.close();


        System.out.println("Print from file");

        // read file
        List<Person> readedPersons = new ArrayList<>();
        FileInputStream fis = new FileInputStream("Task4-fileText.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            readedPersons = (ArrayList<Person>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<String> listNames = readedPersons.stream().map(person -> person.getSurName() + " - " + person.getName()).collect(Collectors.toList());

        listNames.forEach(System.out::println);

        int valueForWriter = 1;
        Properties properties = new Properties();
        for (Person person : notDoublePerson) {
            String name = person.getName();
            String surName = person.getSurName();
            properties.setProperty("surName" + valueForWriter, surName);
            properties.setProperty("name" + valueForWriter, name);
            MethodsForPerson.saveProperties(properties);
            valueForWriter++;
        }

        System.out.println("--------------Print from properties--------------");

        for (int i = 1; i <= notDoublePerson.size(); i++) {
            ResourceBundle bundle = ResourceBundle.getBundle("file", Locale.ENGLISH);
            System.out.println(bundle.getString("surName" + i) + "-" + bundle.getString("name" + i));
        }
    }
}