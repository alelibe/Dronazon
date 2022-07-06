package ServerAmministratore.services;

import ServerAmministratore.DroneListElement;
import ServerAmministratore.DroneList;
import ServerAmministratore.InfoDrone;
import ServerAmministratore.StatList;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/drone")
public class DroneService {

    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDroneList(){
        //InfoDrone response = new InfoDrone(DroneList.getInstance());

        return Response.ok(DroneList.getInstance()).build();
        //return Response.ok(response).build();

    }

    //permette di inserire un drone (ID,indirizzo e porta)
    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addDrone(DroneListElement u){
        int result = DroneList.getInstance().add(u);
        if(result == 1) {
            InfoDrone response = new InfoDrone(DroneList.getInstance());
            response.setPosition();
            return Response.ok(response).build(); //drone aggiunto e restituisco la lista dei droni presenti
        }
        return Response.status(Response.Status.CONFLICT).build(); //esiste già un altro drone con lo stesso ID
    }

    //permette di rimuovere un drone dalla lista
    @Path("delete/{id}")
    @DELETE
    public Response deleteDrone(@PathParam("id") int id){
        int result = DroneList.getInstance().remove(id);
        if(result == 1)
            return Response.ok().build(); //il drone è stato rimosso correttamente
        return Response.status(Response.Status.NOT_FOUND).build(); //il drone non è presente nella lista

    }



}
