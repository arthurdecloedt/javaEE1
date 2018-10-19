/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import rental.Car;
import rental.CarType;
import rental.RentalStore;

/**
 *
 * @author arthurdecloedt
 */
@Stateless
public class ManagerSession implements ManagerSessionRemote {

    @Override
    public Collection<CarType> getAvailableCarTypes(String company) {
        return RentalStore.getRental(company).getCarTypes();
    }

    @Override
    public int getNumberOfReservations(String company, CarType type) {
        Collection<Reservation> resColl= RentalStore.getRental(company).
        int n=0;
        for (Car car : carColl){
            if (car.getType().equals(type)) n++;
        }
        return n;
    }
    
    
    
    
    
}
