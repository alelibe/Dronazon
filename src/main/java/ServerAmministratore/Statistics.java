package ServerAmministratore;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Statistics {
    private Long timestamp;
    private float averageDeliveries;
    private float averageKm;
    private float averagePollution;
    private float averageBattery;

    public Statistics(){}

    public Statistics(Long timestamp, float averageDeliveries, float averageKm, float averagePollution, float averageBattery){
        this.timestamp = timestamp;
        this.averageDeliveries = averageDeliveries;
        this.averageKm = averageKm;
        this.averagePollution = averagePollution;
        this.averageBattery = averageBattery;
    }

    public void setTimestamp(Long timestamp){
        this.timestamp = timestamp;
    }

    public Long getTimestamp(){
        return timestamp;
    }

    public void setAverageDeliveries(float averageDeliveries){
        this.averageDeliveries = averageDeliveries;
    }

    public float getAverageDeliveries(){
        return averageDeliveries;
    }

    public void setAverageKm(float averageKm){
        this.averageKm = averageKm;
    }

    public float getAverageKm(){
        return averageKm;
    }

    public void setAveragePollution(float averagePollution){
        this.averagePollution = averagePollution;
    }

    public double getAveragePollution(){
        return averagePollution;
    }

    public void setAverageBattery(float averageBattery){
        this.averageBattery = averageBattery;
    }

    public float getAverageBattery(){
        return averageBattery;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "timestamp=" + timestamp +
                ", averageDeliveries=" + averageDeliveries +
                ", averageKm=" + averageKm +
                ", averagePollution=" + averagePollution +
                ", averageBattery=" + averageBattery +
                '}';
    }
}
