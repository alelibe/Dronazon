package Drone;

import Drone.Master.*;
import ServerAmministratore.DroneListElement;

import java.util.ArrayList;
import java.util.List;

public class ViewDroneNetwork {
    private int myId;
    private int porta;
    private int num_consegne;
    private double km;
    private DroneListElement drone_master;
    private int battery;
    private boolean master;
    private boolean participant;
    private boolean consegna;
    private boolean election;
    private DroneListElement mySucc;
    private ArrayList<Integer> position;
    private List<DroneListElement> droniInNetwork; //contiene la lista dei droni aggiornata
    private SubMaster subMaster;
    private OrderConsumer orderConsumer;
    private workerGlobalStat workerGlobalStat;
    private InfoMaster infoMaster;
    private GlobalStat globalStat;




    public ViewDroneNetwork(int myId, int porta, DroneListElement drone_master, DroneListElement mySucc, boolean master,
                            ArrayList<DroneListElement> droniInNetwork, ArrayList<Integer> position, InfoMaster infoMaster,GlobalStat globalStat){
        this.myId = myId;
        this.porta = porta;
        this.mySucc = mySucc;
        this.drone_master = drone_master;
        this.battery = 100;
        this.master = master;
        this.participant = false;
        this.droniInNetwork = droniInNetwork;
        this.position = position;
        this.election = false;
        this.infoMaster = infoMaster;
        this.globalStat = globalStat;
        num_consegne = 0;
        km = 0;
    }



    public synchronized void addDrone(DroneListElement drone){
        //inserisco in modo ordinato
        for (int i = 0; i < droniInNetwork.size(); i++) {
            if (droniInNetwork.get(i).getID() < drone.getID()) continue;
            if(!(droniInNetwork.contains(drone)))
                droniInNetwork.add(i, drone);
        }

        droniInNetwork.add(drone);
    }

    public synchronized void setNum_consegne() {
        this.num_consegne = this.num_consegne + 1;
    }

    public int getNum_consegne() {
        return num_consegne;
    }

    public synchronized void setKm(double km) {
        this.km = this.km + km;
    }

    public double getKm() {
        return km;
    }

    public synchronized void removeDrone(DroneListElement drone){
        droniInNetwork.remove(drone);
    }

    public synchronized DroneListElement findMyNewSucc(){
        for(DroneListElement d : droniInNetwork){
            if(d.getID() > myId)
                return d;
        }

        return  droniInNetwork.get(0);
    }

    public synchronized ArrayList<DroneListElement> getDroniInNetwork(){
        ArrayList<DroneListElement> droniListCloned = new ArrayList<DroneListElement>(droniInNetwork);
        return droniListCloned;
    }

    public int getMyId(){
        return myId;
    }

    public int getPorta() {
        return porta;
    }

    public DroneListElement get_mySucc(){
        return mySucc;
    }

    public DroneListElement get_master() {
        return drone_master;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public synchronized void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }

    public synchronized void set_mySucc(DroneListElement mySucc){
        this.mySucc = mySucc;
    }

    public void setId_master(int drone_master) {
        synchronized (droniInNetwork){
            for(DroneListElement d : droniInNetwork){
                if(d.getID() == drone_master) {
                    this.drone_master = d;
                    break;
                }
            }
        }
    }


    public void startMasterThreads(){
        QueueOrdini queueOrdini = new QueueOrdini();
        subMaster = new SubMaster(queueOrdini);
        orderConsumer = new OrderConsumer(queueOrdini,infoMaster);
        workerGlobalStat = new workerGlobalStat(globalStat,infoMaster);

        subMaster.start();
        orderConsumer.start();
        workerGlobalStat.start();
    }

    public void closeMasterThreads() throws InterruptedException {
        infoMaster.removeDrone(myId); //mi tolgo in modo che gli ordini pendenti non vengono assegnati a me
        subMaster.waitExit();
        System.out.println("Disconnessione dal broker..");

        orderConsumer.join();
        Drone.sendStat = 1; //questo serve per avvisare il thread workerGlobalStat per mandare le ultime stat al server
        workerGlobalStat.join();
    }


    public int getBattery() {
        return battery;
    }

    public void setBattery() {
        this.battery = this.battery - 10;
    }

    public synchronized void setMaster(boolean master) {
        this.master = master;
    }

    public boolean isMaster() {
        return master;
    }

    public synchronized void setParticipant(boolean participant) {
        this.participant = participant;
    }

    public boolean isParticipant() {
        return participant;
    }

    public boolean isConsegna() {
        return consegna;
    }

    public synchronized void setConsegna(boolean consegna) {
        this.consegna = consegna;
        notifyAll();
    }

    public synchronized void setElection(boolean election) {
        this.election = election;
        notifyAll();
    }

    public boolean isElection() {
        return election;
    }
}
