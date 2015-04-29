import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 *  Uses create() method, and uses a different logic
 *  on scenarios like onNext, onError, onCompleted.
 *
 *  Creates an {@link Observable} which pushes values to the subscriber.
 */
public class example3 {

    public static void main(String[] args){

        //create your source of data that will emit to subscriber/observers
        Observable myObservable = Observable.create(new Observable.OnSubscribe(){

            @Override
            public void call(Object subscriber) {
                Subscriber mySubscriber = (Subscriber)subscriber;

                for(int i = 0 ; i < 10; i++){
                    //if my subscriber was subscribed
                    if(!mySubscriber.isUnsubscribed()){
                        mySubscriber.onNext("Pushed value " + i);
                    }
                }

                if(!mySubscriber.isUnsubscribed()){
                    mySubscriber.onCompleted();
                }

            }
        });

        //subscribe to your Observable
        myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        },
        new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("Something went wrong the observable");
            }
        },
        new Action0() {
            @Override
            public void call() {
                System.out.println("No more values will be pushed.");
            }
        });

    }


}
