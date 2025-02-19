package movieTicket.app.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Ticket {

    private Long id;
    private BigDecimal price;
    private LocalDate purchaseDate;
    private LocalDate sessionDate;
    private boolean isVip;


    public Ticket(BigDecimal price, LocalDate purchaseDate, LocalDate sessionDate, boolean isVip) {
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.sessionDate = sessionDate;
        this.isVip = isVip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                ", sessionDate=" + sessionDate +
                ", isVip=" + isVip +
                '}';
    }

}
