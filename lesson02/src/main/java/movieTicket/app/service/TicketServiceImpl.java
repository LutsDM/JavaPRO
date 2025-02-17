package movieTicket.app.service;

import movieTicket.app.repository.TicketRepository;
import movieTicket.app.domain.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {


    TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    @Override
    public List<Ticket> getAll() {
        return ticketRepository.getAllTickets();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findTicket(id);
    }

    @Override
    public List<Ticket> getByPurchaseDate(LocalDate purchaseDate) {
        return ticketRepository.getAllTickets().stream()
                .filter(ticket -> ticket.getPurchaseDate().equals(purchaseDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBySessionDate(LocalDate sessionDate) {
        return ticketRepository.getAllTickets().stream()
                .filter(ticket -> ticket.getSessionDate().equals(sessionDate))
                .collect(Collectors.toList());
    }

    @Override
    public int getCountOfVip() {
        return (int) ticketRepository.getAllTickets().stream()
                .filter(ticket -> ticket.isVip())
                .count();
    }

    @Override
    public BigDecimal getAvrPrice() {
        return new BigDecimal(
                getAll().stream()
                        .mapToDouble(ticket -> ticket.getPrice().doubleValue())
                        .average()
                        .orElse(0.0)
        );
    }
}
