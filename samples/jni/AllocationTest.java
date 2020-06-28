public class AllocationTest {

    static native void objectsAllocationTest();

    public static void main(String[] args) {
        System.loadLibrary("NativeLib");
        objectsAllocationTest();
    }
}

class BornInNative {

    private static final int MAX = Integer.MAX_VALUE;

    int number;

    public BornInNative(int num) {
        this.number = num;
    }

    public boolean areYouReady() {
        return number == MAX;
    }
}