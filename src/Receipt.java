public class Receipt {
    Wash wash;

    private String makeLogo() {
        return  "             Thank you for washing your car at\n"+
                "   _____                       _____ _     _            \n" +
                "  / ____|                     / ____| |   (_)           \n" +
                " | (___  _   _ _ __   ___ _ _| (___ | |__  _ _ __   ___ \n" +
                "  \\___ \\| | | | '_ \\ / _ \\ '__\\___ \\| '_ \\| | '_ \\ / _ \\\n" +
                "  ____) | |_| | |_) |  __/ |  ____) | | | | | | | |  __/\n" +
                " |_____/ \\__,_| .__/ \\___|_| |_____/|_| |_|_|_| |_|\\___|\n" +
                "              | |                                       \n" +
                "              |_|\n";
    }

    private String makeSeparator(int desiredLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < desiredLength; i++) {
            sb.append("=");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "" + makeLogo() + "\n" + makeSeparator(23) +
                "Type of Wash:" + wash.getType().toString() + "\n" +
                "Price: " + wash.getPrice() + "DKK";
    }
}
