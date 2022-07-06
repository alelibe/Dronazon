package Drone.Master;


import java.util.ArrayList;

public class InfoMaster {
    private ArrayList<InfoMasterElem> infoMaster;

    public InfoMaster(){
        infoMaster = new ArrayList<InfoMasterElem>();
    }

    public void add(InfoMasterElem drone){
        synchronized (infoMaster) {

            infoMaster.add(drone);
        }
    }

    public void removeDrone(int id_drone){
        synchronized (infoMaster) {
            /*for (InfoMasterElem i : infoMaster) {
                if (i.getDrone().getID() == id_drone)
                    infoMaster.remove(i);
            }*/

            infoMaster.removeIf(i -> (i.getDrone().getID() == id_drone));
        }
    }


    public void setPosition(int id_drone, ArrayList<Integer> updatePos){
        synchronized (infoMaster) {
            for (InfoMasterElem i : infoMaster) {
                if (i.getDrone().getID() == id_drone)
                    i.setPosition(updatePos);
            }
        }
    }


    public void setBattery(int id_drone, int battery){
        synchronized (infoMaster) {
            for (InfoMasterElem i : infoMaster) {
                if (i.getDrone().getID() == id_drone)
                    i.setBattery(battery);
            }
        }
    }



    public void setConsegna(int id_drone, int consegna){
        synchronized (infoMaster) {
            for (InfoMasterElem i : infoMaster) {
                if (i.getDrone().getID() == id_drone)
                    i.setConsegna(consegna);
            }
        }
    }



    /*public boolean isContains(InfoMasterElem drone){
        for(InfoMasterElem d : infoMaster){
            if(d.getDrone().getID() == drone.getDrone().getID())
                return true;
        }

        return false;
    }*/


    public ArrayList<InfoMasterElem> getInfoMaster() {
        return infoMaster;
    }
}
