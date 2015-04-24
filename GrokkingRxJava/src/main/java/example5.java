import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by matie on 23/04/15.
 */
public class example5 {
    public static void main(String[] args){

        Observable.just("Hello World!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "-Mo";
                    }
                })
        .subscribe(s -> System.out.println(s));

        //another way of doing it - simpler
        Observable.just("Hello World!")
                .map(s -> s + "-Mo all 8")
                .subscribe(s -> System.out.println(s));

        //Baby Steps
        Observable<String> myObservable = Observable.just("Hello Baby World");
        Action1<String> myOnNext = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        //our Observable map function
        Func1<String,String> myMapFunction = new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "-BabyMo";
            }
        };
        //start emitting once you subscribe
        myObservable.map(myMapFunction).subscribe(myOnNext);
    }
}
