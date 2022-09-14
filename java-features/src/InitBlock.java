public class InitBlock {

    static {
        System.out.println("static 1");
    }

    {
        System.out.println("1");
    }

    public InitBlock() {
        System.out.println("3");
    }

    {
        System.out.println("2");
    }

    static {
        System.out.println("static 2");
    }
}
