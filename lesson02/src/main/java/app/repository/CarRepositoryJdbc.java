package app.repository;

import app.domain.Car;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;

public class CarRepositoryJdbc implements CarRepository {

    private Connection getConnection() {
        try {
            // Эта строка кода подгружает необходимый драйвер БД
            // в память работающего приложения, чтобы драйвер был
            // доступен во время выполнения программы.
            Class.forName(DB_DRIVER_PATH);
            String dbUrl = DB_ADDRESS + DB_NAME;
            return DriverManager.getConnection(dbUrl, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Car save(Car car) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO car (brand, year, price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, car.getBrand());
                statement.setInt(2, car.getYear());
                statement.setBigDecimal(3, car.getPrice());
                statement.executeUpdate();


                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return car;
    }


    @Override
    public List<Car> findAll() {
        // Открываем соединение с БД
        try (Connection connection = getConnection()) {

            // Создаём запрос, который собираемся отправить в БД
            String query = "SELECT * FROM car;";

            // Получаем объект, который умеет отправлять запросы в БД
            Statement statement = connection.createStatement();

            // Отправляем запрос в БД и получаем от неё ответ
            ResultSet resultSet = statement.executeQuery(query);

            List<Car> cars = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String brand = resultSet.getString("brand");
                int year = resultSet.getInt("year");
                BigDecimal price = resultSet.getBigDecimal("price");

                cars.add(new Car(id, brand, year, price));
            }

            return cars;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car findById(Long id) {
        String query = "SELECT * FROM car WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Car(
                        resultSet.getLong("id"),
                        resultSet.getString("brand"),
                        resultSet.getInt("year"),
                        resultSet.getBigDecimal("price") // Читаем BigDecimal
                );
            }
            return null; // Если запись не найдена

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car car) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE car SET brand = ?, year = ?, price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, car.getBrand());
                statement.setInt(2, car.getYear());
                statement.setBigDecimal(3, car.getPrice());
                statement.setLong(4, car.getId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new RuntimeException("Car with id " + car.getId() + " not found");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM car WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}