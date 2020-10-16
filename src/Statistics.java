public class Statistics {
    //Fields
    private int economy , deLuxe , standard;



    @Override   //Override toString to print statistics
    public String toString(){
        return "Economy: " + economy +"\n" +
                "De Luxe: " + deLuxe +"\n" +
                "Standard: " + standard;
    }

    public void logWash(Wash wash){
        switch (wash.getType()){
            case ECONOMY:
                economy++;
                break;
            case STANDARD:
                standard++;
                break;
            case DELUXE:
                deLuxe++;
                break;
            default:
                try {
                    throw new StatisticsException("Error when adding wash to statistics.");
                } catch (StatisticsException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
