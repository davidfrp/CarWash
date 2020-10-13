public class Statistics {

    private int economy , deLuxe , standard;



    @Override
    public String toString(){
        return "Economy: " + economy +"\n" +
                "De Luxe: " + deLuxe +"\n" +
                "Standard: " + standard;
    }

    public void logWash(WashType  washtype){
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
                    throw new StatisticsException("Error with adding wash to statistics");
                } catch (StatisticsException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
