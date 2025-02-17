package movieTicket.app.repository;

import movieTicket.app.domain.Ticket;

import java.util.List;

public interface TicketRepository {
Ticket save (Ticket ticket);
List <Ticket> getAllTickets();
Ticket findTicket (Long id);
void deleteTicket (Long id);
void updateTicket (Long id, Ticket ticket);
}
