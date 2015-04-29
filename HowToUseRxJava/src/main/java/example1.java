import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * SECTION: Transforming Observables with Operators
 *
 * Created by matie on 26/04/15.
 */
public class example1 {
    public static void main(String[] args){
        //prints from 11 to 15
        customObservableNonBlocking().skip(10).take(5)
                .map(num -> num + 1)
                .subscribe(modNum -> System.out.println(modNum));
    }

    //create our custom asynchronous observable
    private static Observable<Integer> customObservableNonBlocking(){
        return Observable.create(subscriber -> customAsyncSubscriber(subscriber));
    }

    private static void customAsyncSubscriber(Subscriber<? super Integer> subscriber) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 20 ; i++){
                    if(!subscriber.isUnsubscribed()){
                        subscriber.onNext(i);
                    }
                }
                if(!subscriber.isUnsubscribed())
                    subscriber.onCompleted();
            }
        });
        thread.start();
    }


}
