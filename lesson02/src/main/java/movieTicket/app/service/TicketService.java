package movieTicket.app.service;

import movieTicket.app.domain.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    Ticket save (Ticket ticket);
    List<Ticket> getAll();
    Ticket getTicketById (Long id);
    List<Ticket> getByPurchaseDate (LocalDate purchaseDate);
    List<Ticket> getBySessionDate (LocalDate sessionDate);
    int getCountOfVip ();
    BigDecimal getAvrPrice ();
}
