public class Machine {

    Database database;
    Statistics statistics;


    public Machine(Database database, Statistics statistics){

    }

    private WashCard createWashCard(int ownerID){
        return new WashCard(ownerID);
    }
}