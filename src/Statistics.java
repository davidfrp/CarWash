public class Statistics {
    //Fields
    private int economy , deLuxe , standard;



    @Override   //Override toString to print statistics
    public String toString(){
        return "Economy: " + economy +"\n" +
                "De Luxe: " + deLuxe +"\n" +
                "Standard: " + standard;
    }

    public void logWash(WashType  washtype){    //Log wash and increase field by one
        switch (washtype.getType()){
            case "ECONOMY":
                economy++;
                break;
            case "STANDARD":
                standard++;
                break;
            case "DELUXE":
                deLuxe++;
                break;
            default:
                try {
                    throw new StatisticsException("Error with adding wash to statistics");  //Error exception
                } catch (StatisticsException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
