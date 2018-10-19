/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Set;
import javax.ejb.Remote;
import rental.CarType;

/**
 *
 * @author arthurdecloedt
 */
@Remote
public interface ManagerSessionRemote {

    Collection<CarType> getAvailableCarTypes(String Company);

    int getNumberOfReservations(String company, CarType type);
    
}
