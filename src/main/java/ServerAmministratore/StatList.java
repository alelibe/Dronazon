package ServerAmministratore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class StatList {
    @XmlElement(name="stat_list")
    private List<Statistics> statList;

    private static StatList instance;

    private StatList() {
        statList = new ArrayList<Statistics>();
    }

    //singleton
    public synchronized static StatList getInstance(){
        if(instance==null)
            instance = new StatList();
        return instance;
    }

    public synchronized List<Statistics> getStatList() {
        return new ArrayList<>(statList);
    }

    public synchronized void add(Statistics stat){
        statList.add(stat);
    }


    //restituisce un array con le prime n statistiche
    public ArrayList<Statistics> getFirstNStat(int n){
        List<Statistics> statListCloned;
        synchronized (statList) {
            statListCloned = new ArrayList<Statistics>(statList);
        }

        ArrayList<Statistics> result = new ArrayList<>();
        if(n <= statListCloned.size()) {
            for (int i = 0; i < n; i++) {
                Statistics stat = statListCloned.get(i);
                result.add(stat);
            }
            return result;
        }

        return null; //se le statitiche presenti nella lista sono minore di n

    }

    //restituisce la media del numero di consegne effettuate tra due timestamp t1 e t2 oppure un errore
    public float getDeliveryT1toT2(Long t1, Long t2){
        if(t1 > t2) return -1;
        List<Statistics> statListCloned = null;
        synchronized (statList) {
            statListCloned = new ArrayList<Statistics>(statList);
        }
        float somma = 0;
        float nStat = 0;
        float average;
        for(Statistics s : statListCloned){
            Long t = s.getTimestamp();
            if(t >= t1 && t <= t2) {
                somma = somma + s.getAverageDeliveries();
                nStat++;
            }
        }

        if(nStat == 0) return 0;
        average = somma/nStat;
        return average;

    }



    public float getKmTot1Tot2(Long t1, Long t2){
        if(t1 > t2) return -1;
        List<Statistics> statListCloned = new ArrayList<Statistics>(statList);
        float somma = 0;
        int nStat = 0;
        float average = 0;
        for(Statistics s : statListCloned){
            Long t = s.getTimestamp();
            if(t >= t1 && t <= t2) {
                somma = somma + s.getAverageKm();
                nStat++;
            }
        }

        if(nStat == 0) return 0;
        average = somma/nStat;
        return average;

    }


}
