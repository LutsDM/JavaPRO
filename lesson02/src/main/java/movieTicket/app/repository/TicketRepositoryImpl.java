package movieTicket.app.repository;

import movieTicket.app.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private final List<Ticket> database = new ArrayList<>();


    @Override
    public Ticket save(Ticket ticket) {
        database.add(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(database);
    }

    @Override
    public Ticket findTicket(Long id) {
        return database.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    @Override
    public void deleteTicket(Long id) {
        database.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .ifPresent(ticket -> database.remove(ticket));

    }
    @Override
    public void updateTicket(Long id, Ticket ticket) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId().equals(id)) {
                database.set(i, ticket);
                break;
            }
        }
    }
}
