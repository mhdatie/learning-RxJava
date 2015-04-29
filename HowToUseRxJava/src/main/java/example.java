import rx.Observable;
import rx.functions.Action1;

public class example {

    public static void main(String[] args){
        hello("Bob", "Jenny", "Joe");
    }

    private static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });
    }

}
