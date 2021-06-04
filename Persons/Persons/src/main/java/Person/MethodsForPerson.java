package Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class MethodsForPerson {

    static String getRandomName() {
        String[] names = {"Ivan", "Fedor", "Alex", "Boris", "Semen", "Vasya", "Kolya"};
        int randomName = (int) (Math.random() * 7);
        return names[randomName];
    }

    static String getRandomSurName() {
        String[] names = {"Ivanov", "Fedorov", "Alexandrov", "Borisov", "Semenov", "Vasiliev", "Nikolaev"};
        int randomSurName = (int) (Math.random() * 7);
        return names[randomSurName];
    }

    static List<Person> personList(int minAge, int maxAge) {
        List<Person> personList = new ArrayList<>();
        Random random = new Random();
        int diff = maxAge - minAge;
        for (int i = 0; i < 100; i++) {
            int age = random.nextInt(diff + 1) + minAge;
            Person person = new Person(getRandomName(), getRandomSurName(), age);
            personList.add(person);
        }
        return personList;
    }

    static List<Person> withoutDuble(List<Person> personsList) {
        for (int i = 0; i < personsList.size(); i++) {
            for (int j = 0; j < personsList.size(); j++) {
                if (i == j) {

                } else if (personsList.get(j).getSurName().equals(personsList.get(i).getSurName())
                        && personsList.get(j).getName().equals(personsList.get(i).getName())) {
                    personsList.remove(j);
                }
            }
        }
        return personsList;
    }

    static void saveProperties(Properties p) throws IOException {
        FileOutputStream fr = new FileOutputStream("C:\\Users\\esilv\\EgorSilvanovich\\Persons\\src\\main\\resources\\file.properties");
        p.store(fr, null);
        fr.close();
    }
}