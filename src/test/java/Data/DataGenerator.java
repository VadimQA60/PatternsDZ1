package Data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DataGenerator {

   static Faker faker = new Faker(new Locale("ru"));

   public String generateDate(int daysToAdd) {
      LocalDate currenteDate = LocalDate.now();
      LocalDate deliveryDate = currenteDate.plusDays(daysToAdd);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      String deliveryDateStr = deliveryDate.format(formatter);
      return deliveryDateStr;

   }

   public static String generateName() {
      return faker.name().name();
   }

   public static String generatePhoneNumber() {
      return faker.numerify("+###########");

   }

   public static final List<String> cities = Arrays.asList(
           "Москва", "Санкт-Петербург", "Псков", "Воронеж", "Великий Новгород", "Смоленск", "Краснодар" );

   public static String generateCity() {
      return cities.get(faker.random().nextInt(cities.size()));
   }

}
