package ServerAmministratore.services;

import ServerAmministratore.StatList;
import ServerAmministratore.Statistics;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/stat")
public class StatisticService {

    //restituisce la lista di statistiche
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getStatList(){
        return Response.ok(StatList.getInstance()).build();

    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addStat(Statistics stat){
        StatList.getInstance().add(stat);
        return Response.ok(StatList.getInstance()).build();
    }

    @Path("get/{n}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getNStat(@PathParam("n") int n){
        ArrayList<Statistics> result = StatList.getInstance().getFirstNStat(n);
        if(result != null)
            return Response.ok(result).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Path("get_delivery")
    @GET
    @Produces({"text/plain"})
    public Response getDelFromt1Tot2(@QueryParam("from") Long t1, @QueryParam("to") Long t2){
        float result = StatList.getInstance().getDeliveryT1toT2(t1,t2);
        String media = String.valueOf(result);
        if(result == -1)
            return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(media).build();
    }

    @Path("get_km")
    @GET
    @Produces({"text/plain"})
    public Response getKmFromt1Tot2(@QueryParam("from") Long t1, @QueryParam("to") Long t2){
        float result = StatList.getInstance().getKmTot1Tot2(t1,t2);
        String media = String.valueOf(result);
        if(result == -1)
            return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(media).build();
    }


}
