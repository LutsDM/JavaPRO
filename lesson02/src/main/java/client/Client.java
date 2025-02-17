package client;

import app.controller.CarController;
import app.domain.Car;
import app.repository.CarRepository;
import app.repository.CarRepositoryMap;
import app.service.CarService;
import app.service.CarServiceImpl;

public class Client {

    public static void main(String[] args) {

        CarRepository repository = new CarRepositoryMap();
        CarService service = new CarServiceImpl(repository);
        CarController controller = new CarController(service);

        // Попробуем получить среднюю цену автомобиля
        System.out.println(controller.getAveragePrice());

        // Сохраняем автомобили в БД
        controller.save("Toyota", 2020, 30000);
        controller.save("Honda", 2022, 45000);
        controller.save("Mercedes", 2018, 27000);

        System.out.println("Список всех автомобилей:");
        controller.getAll().forEach(System.out::println);

        System.out.println("Автомобиль с идентификатором 2:");
        System.out.println(controller.getById(2L));

        System.out.println("Средняя цена автомобиля в продаже:");
        System.out.println(controller.getAveragePrice());
    }
}
