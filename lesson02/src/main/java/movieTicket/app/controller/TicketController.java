package movieTicket.app.controller;

import movieTicket.app.domain.Ticket;
import movieTicket.app.service.TicketService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public Ticket saveTicket(Ticket ticket) {
        return ticketService.save(ticket);
    }


    public List<Ticket> getAllTickets() {
        return ticketService.getAll();
    }


    public Ticket getTicketById(Long id) {
        return ticketService.getTicketById(id);
    }


    public List<Ticket> getTicketsByPurchaseDate(LocalDate purchaseDate) {
        return ticketService.getByPurchaseDate(purchaseDate);
    }


    public List<Ticket> getTicketsBySessionDate(LocalDate sessionDate) {
        return ticketService.getBySessionDate(sessionDate);
    }


    public int getCountOfVipTickets() {
        return ticketService.getCountOfVip();
    }


    public BigDecimal getAverageTicketPrice() {
        return ticketService.getAvrPrice();
    }
}
