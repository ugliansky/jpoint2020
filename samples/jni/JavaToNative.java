public class JavaToNative {
  
    static native void goNative();

    static native void goThere(Callback andBackAgain);

    public static void main(String[] args) {
        System.loadLibrary("NativeLib");
        goThere(new Callback("Eagles"));  
    }

    static class Callback {
      
        private final String transport;

        public Callback(String transport) {
            this.transport = transport;
        }

        public void call() {
            System.out.println("Ok, we are in Shire again! Returned by " + transport);
        }
    }
}
