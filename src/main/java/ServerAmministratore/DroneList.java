package ServerAmministratore;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DroneList {

   // @XmlElement(name="drone_list")
    private List<DroneListElement> droneList;

    private static DroneList instance;

    public DroneList() {
        droneList = new ArrayList<DroneListElement>();
    }

    //singleton
    public synchronized static DroneList getInstance(){
        if(instance==null)
            instance = new DroneList();
        return instance;
    }

    public synchronized ArrayList<DroneListElement> getDroneList() {
        return new ArrayList<>(droneList);
    }

    public synchronized int add(DroneListElement droneListElement){
        for(DroneListElement d : droneList){
            if(d.getID() == droneListElement.getID())
                return 0;
        }

        //inserisco in modo ordinato
        for (int i = 0; i < droneList.size(); i++) {
            // if the element you are looking at is smaller than x,
            // go to the next element
            if (droneList.get(i).getID() < droneListElement.getID()) continue;
            // otherwise, we have found the location to add x
            droneList.add(i, droneListElement);
            return 1;
        }
        // we looked through all of the elements, and they were all
        // smaller than x, so we add ax to the end of the list
        droneList.add(droneListElement);
        return 1;
    }

    public synchronized int remove(int id){
        for(DroneListElement d : droneList){
            if(d.getID() == id){
                droneList.remove(d);
                return 1;
            }
        }
        return 0;
    }



}
