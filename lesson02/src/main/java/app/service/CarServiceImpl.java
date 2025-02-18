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
        return new BigDecimal(getAll()
                .stream()
                .mapToDouble(x -> x.getPrice().doubleValue())
                .average()
                .orElse(0.0));
    }
}
