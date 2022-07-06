package ServerAmministratore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Random;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoDrone {

    private DroneList infoDrone;
    private ArrayList<Integer> position; //position[0] = x, position[1] = y

    public InfoDrone(){}


    public InfoDrone(DroneList infoDrone){
        this.infoDrone = infoDrone;
        position = new ArrayList<>(2);

    }


    public void setInfoDrone(DroneList infoDrone){
        this.infoDrone = infoDrone;
    }

    public void setPosition(){
        int min = 1;
        int max = 10;

        Random random = new Random();

        int x = random.nextInt((max - min) + 1) + min;
        int y = random.nextInt((max - min) + 1) + min;

        position.add(x);
        position.add(y);
    }

    public DroneList getInfoDrone(){
        return infoDrone;
    }

    public ArrayList<Integer> getPosition(){
        return position;
    }

}
