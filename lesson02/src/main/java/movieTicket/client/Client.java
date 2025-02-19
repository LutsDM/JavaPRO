package movieTicket.client;

import movieTicket.app.controller.TicketController;
import movieTicket.app.repository.TicketRepository;
import movieTicket.app.repository.TicketRepositoryImpl;
import movieTicket.app.service.TicketService;
import movieTicket.app.service.TicketServiceImpl;
import movieTicket.app.domain.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Client {

    public static void main(String[] args) {

        TicketRepository repository = new TicketRepositoryImpl();
        TicketService service = new TicketServiceImpl(repository);
        TicketController controller = new TicketController(service);

        //Билеты
        Ticket ticket01 = new Ticket(new BigDecimal("15.99"), LocalDate.of(2025, 2, 16), LocalDate.of(2025, 2, 19), true);
        Ticket ticket02 = new Ticket(new BigDecimal("25.50"), LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 12), false);
        Ticket ticket03 = new Ticket(new BigDecimal("10.75"), LocalDate.of(2025, 4, 5), LocalDate.of(2025, 4, 6), true);
        Ticket ticket04 = new Ticket(new BigDecimal("20.00"), LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3), false);
        Ticket ticket05 = new Ticket(new BigDecimal("30.99"), LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 18), true);



        // Сохраняем билет
        controller.saveTicket(ticket01);
        controller.saveTicket(ticket02);
        controller.saveTicket(ticket03);
        controller.saveTicket(ticket04);
        controller.saveTicket(ticket05);

        // Получаем все билеты
        System.out.println("All tickets: " + controller.getAllTickets());

        // Получаем билет по ID
        Ticket fetchedTicket = controller.getTicketById(2L);
        System.out.println("Fetched ticket: " + fetchedTicket);

        // Получаем билеты по дате покупки
        System.out.println("Tickets by purchase date: " + controller.getTicketsByPurchaseDate(LocalDate.of(2025, 3, 10)));

        // Получаем билеты по дате сеанса
        System.out.println("Tickets by session date: " + controller.getTicketsBySessionDate(LocalDate.of(2025, 6, 18)));

        // Получаем количество VIP билетов
        System.out.println("VIP ticket count: " + controller.getCountOfVipTickets());

        // Получаем среднюю цену билетов
        System.out.println("Average ticket price: " + controller.getAverageTicketPrice().setScale(2, BigDecimal.ROUND_HALF_UP));

    }
}

