package frontendproductioncode;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s %n");
        FrontendComponent myapp=new FrontendComponent();
        myapp.frontendView();
    }
}
