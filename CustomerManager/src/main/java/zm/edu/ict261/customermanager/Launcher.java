package zm.edu.ict261.customermanager;

/**
 * Plain entry point. It does NOT extend Application, which avoids the
 * "JavaFX runtime components are missing" error when running from IntelliJ
 * with JavaFX supplied as Maven dependencies.
 */
public class Launcher {
    public static void main(String[] args) {
        MainApp.main(args);
    }
}
