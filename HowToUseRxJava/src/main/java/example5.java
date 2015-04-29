import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Error handling example by reporting to the observer.
 */
public class example5 {
    public static void main(String[] args){

        //What and when our source will emmit values.
        Observable.OnSubscribe<String> subscription = new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

                Subscriber mySubscriber = subscriber;

                try{

                    for(int i = 0 ; i < 50 ; i++){
                        if(!mySubscriber.isUnsubscribed()){
                            mySubscriber.onNext("Pushed value " + i);
                        }
                        //throw the error after emitting the i'th item.
                        if(i == 5){
                            throw new Throwable("Oops! Someone has pooped.");
                        }
                    }

                    if(!subscriber.isUnsubscribed()){
                        mySubscriber.onCompleted();
                    }

                }catch (Throwable throwable){
                    mySubscriber.onError(throwable);
                }

            }
        };

        //Create an observable by passing our created OnSubscribe instance.
        Observable createdObservable = Observable.create(subscription);

        //Subscribe to our Observable or source to listen to events.
        createdObservable.subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("Oops! Someone has pooped.");
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("No more values will be pushed.");
                    }
                }
        );

    }
}
