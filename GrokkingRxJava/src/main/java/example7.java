import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * See example 8 for Lambda example
 * Created by matie on 25/04/15.
 */
public class example7 {
    public static void main(String[] args){

        //flatMap() example (the ugly way)
        /**
         * <p>flatMap() takes as an input the emitted list from the <code>query</code>
         * function and returns a set of new Observables using the from() operator
         * </p>
         *
         * <p>The Subscriber will subscribe to the newly created observables
         * and prints out the emitted item(s), which in this case is a single variable</p>
         *
         */
        query("rx").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }

    /**
     * This method takes a {@link String} value, and returns an Observable
     * that emits a list of url composed from the passed parameter.
     *
     * @param text
     * @return {@link Observable}
     */
    private static Observable<List<String>> query(String text){
        Observable<List<String>> myObservable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> myList = new ArrayList<String>();
                for(int i = 0 ; i < 100 ; i++){
                    myList.add(text+ i + ".com");
                }
                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(myList);
                    subscriber.onCompleted();
                }
            }
        });

        return myObservable;
    }




}
