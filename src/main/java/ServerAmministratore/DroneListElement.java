package ServerAmministratore;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DroneListElement {
    private int id;
    private String address;
    private int porta;

    public DroneListElement(){}

    public DroneListElement(int id, String address, int porta){
        this.id = id;
        this.address = address;
        this.porta = porta;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setID(int id){ this.id = id; }

    public int getID(){
        return id;
    }

    public void setPorta(int porta){
        this.porta = porta;
    }

    public int getPorta(){
        return porta;
    }

    @Override
    public String toString() {
        return "DroneListElement{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", porta=" + porta +
                '}';
    }
}