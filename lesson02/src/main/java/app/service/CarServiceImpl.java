package app.service;

import app.domain.Car;
import app.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    @Override
    public Car getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BigDecimal getCarAveragePrice() {
        return new BigDecimal(getAll() // Получаем все автомобили из БД
                .stream() // Создаём стрим, то есть последовательность всех автомобилей
                .mapToDouble(x -> x.getPrice().doubleValue()) // Преобразуем последовательность автомобилей в последовательность цен
                .average() // Считаем среднюю цену автомобиля
                .orElse(0.0)); // Предусматриваем значение по умолчанию на тот случай, если автомобилей в БД вообще нет
    }
}
