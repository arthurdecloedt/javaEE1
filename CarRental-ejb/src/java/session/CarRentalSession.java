package session;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import rental.CarRentalCompany;
import rental.Quote;
import rental.RentalStore;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    
    private String renter;
    private Set<Quote> currentQuotes=new HashSet<Quote>();
    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }

    @Override
    public Quote createQuote(ReservationConstraints rconst) {
        Set<String> rentalcomps= getAllRentalCompanies();
        Quote quote=null;
        for (String comp : rentalcomps) {
            CarRentalCompany company = RentalStore.getRental(comp);
            try {
                quote = company.createQuote(rconst, "Guest");
                
            }
            catch (ReservationException r){};
        }
        if (quote != null){
            currentQuotes.add(quote);
        }
        return quote;
    }

    @Override
    public Set<Quote> getCurrentQuotes() {
        return currentQuotes;
    }

    @Override
    public Set<Reservation> confirmQuotes() throws ReservationException {
        Set<Reservation> resSet= new HashSet<Reservation> ();

        for (Quote quote : currentQuotes ){
            try {
                resSet.add(RentalStore.getRental(quote.getRentalCompany()).confirmQuote(quote));
            }
            catch(ReservationException r){
            for (Reservation res: resSet){
               RentalStore.getRental(res.getRentalCompany()).cancelReservation(res);
            }
            throw r;
            }
        }
        return resSet;
    }
    
    @Override
    public void setRenterName(String name) {
        if (renter != null) {
            throw new IllegalStateException("The name of this renter is already set.");
        }
        renter = name;
    }
}
 
