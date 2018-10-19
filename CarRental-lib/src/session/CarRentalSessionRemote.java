package session;

import java.util.Set;
import javax.ejb.Remote;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    
    Quote createQuote(ReservationConstraints Rconst);
    
    Set<Quote> getCurrentQuotes();
    
    Set<Reservation> confirmQuotes() throws ReservationException;
    
    void setRenterName(String name);
    
}
