package control;

/**
 * This class creates an instance of the class MyControlImplementation.
 */
public class MyControlFactory {

    private static MyControl instance = null;

    /**
     * Cretes a new instance of class MyControl if newInstance==true and returns it,
     * otherwise it returns the already created instance.
     * @param newInstance creates new instance of class MyControl if true
     * @return instance of class MyControl
     */
    public static MyControl produceControl(boolean newInstance) {
        if (newInstance) return new MyControlImplementation();

        if (MyControlFactory.instance == null) {
            MyControlFactory.instance = new MyControlImplementation();
        }
        return MyControlFactory.instance;
    }

    /**
     * Returns the already created instance of the class MyControl.
     * @return instance of class MyControl
     */
    public static MyControl produceControl() {
        return MyControlFactory.produceControl(false);
    }
}
