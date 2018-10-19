/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;
import rental.Reservation;

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
        Collection<Car> carColl= RentalStore.getRental(company).getCars();
        int n=0;
        
        for (Car car : carColl){
            if (car.getType().equals(type)) n += car.getAllReservations().size();
        }
        return n;
    }

    @Override
    public String getBestCustomer() {
        Collection<CarRentalCompany> carCompColl= RentalStore.getRentals().values();
        Set<Reservation> resSet = new HashSet<Reservation>();
        for (CarRentalCompany carComp : carCompColl){
            Collection<Car> carset=carComp.getCars();
            for (Car car : carset){
                resSet.addAll(car.getAllReservations());
            }
        }
        Map<String,Integer> count=new HashMap<String,Integer>();
        for (Reservation res:resSet){
            if (count.containsKey(res.getCarRenter())) count.put(res.getCarRenter(), count.get(res.getCarRenter())+1);
            else count.put(res.getCarRenter(), 1);
            }
        int n=0;
        String currRenter="None";
        for (String renter: count.keySet()){
            if (count.get(renter)>=n){
                n=count.get(renter);
                currRenter=renter;
            }
                
        }
        return currRenter;
    }
       
}
