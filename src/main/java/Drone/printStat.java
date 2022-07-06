package Drone;

public class printStat extends Thread{
    private ViewDroneNetwork viewDrone;

    public printStat(ViewDroneNetwork viewDrone){
        this.viewDrone = viewDrone;
    }


    @Override
    public void run() {

            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Numero di consegne effettuate: " + viewDrone.getNum_consegne() + "\n"
                        + "Km percorsi: " + viewDrone.getKm() + "\n"
                        + "Batteria residua: " + viewDrone.getBattery());
            }


    }
}
