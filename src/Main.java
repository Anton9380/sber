import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class City {
    private String name;
    private String region;
    private String district;
    private long population;
    private String foundation;

    public City(String name, String region, String district, long population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<City> cities = readCitiesFromFile("cities.txt");

        for (City city : cities) {
            System.out.println(city);
        }
    }

    private static List<City> readCitiesFromFile(String filename) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String region = parts[1].trim();
                    String district = parts[2].trim();
                    long population = Long.parseLong(parts[3].trim());
                    String foundation = parts[4].trim();

                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Некорректный формат строки: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
            e.printStackTrace();
        }
        return cities;
    }
}
