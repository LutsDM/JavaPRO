package app.controller;

import app.domain.Car;
import app.repository.CarRepository;

import app.repository.CarRepositoryHibernate;
import app.repository.CarRepositoryJdbc;
import app.repository.CarRepositoryMap;
import app.service.CarService;
import app.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

// Это сервлет. Сервлет - это такой класс, задача которого принимать
// запросы, обрабатывать их и отдавать клиенту ответы.
public class CarServlet extends HttpServlet {

    private final CarService service;

    public CarServlet() {
        CarRepository repository = new CarRepositoryHibernate();
        service = new CarServiceImpl(repository);
    }

    // Это метод doGet. Для чего он нужен?
    // Когда на наше приложение будет приходить GET-запрос,
    // Tomcat будет создавать Джава-объекты запроса и ответа,
    // затем вызывать наш метод doGet и передать эти объекты в метод аргументами.
    // Наша задача - прочитать всё, что нужно, из объекта req (request)
    // и записать всю информацию для клиента в объект resp (response).
    // После того как метод обработает, Tomcat прочитает всю информацию
    // из объекта resp, упакует её в http-ответ и отправит обратно клиенту.


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = resp.getWriter();
        resp.setContentType("Application/JSON");
        Car car = mapper.readValue(req.getReader(), Car.class);

        if (car.getPrice() == null || car.getBrand() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(String.format("The car must have a brand and a price."));
        } else {
            service.save(car);
            resp.getWriter().write(String.format("Car id:%s brand:'%s' was successfully added.", car.getId(), car.getBrand()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Если в запросе будет присутствовать id, то его значение запишется в переменную.
        // А если id не будет в запросе, то в переменную запишется null.
        String id = req.getParameter("id");
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = resp.getWriter();
        resp.setContentType("Application/JSON");

        if (id == null) {
            // Здесь будем отдавать клиенту все автомобили
            List<Car> cars = service.getAll();
            mapper.writeValue(writer, cars);
        } else {
            // Здесь будем отдавать клиенту один автомобиль, соответствующий id
            Long numericId = Long.parseLong(id);
            Car car = service.getById(numericId);
            mapper.writeValue(writer, car);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);

        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID parameter is required");
        } else {
            resp.getWriter().write(String.format("Car id:%n was deleted", numericId));

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(req.getReader(), Car.class);

        if (car.getId() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID parameter is required");
            return;
        } else {
            service.update(car);
            resp.getWriter().write(String.format("Car was update"));
        }


    }


}
