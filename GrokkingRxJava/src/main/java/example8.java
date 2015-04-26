import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matie on 26/04/15.
 */
public class example8 {
    public static void main(String[] args){
        query("rx-lambda").flatMap(list -> Observable.from(list)).subscribe(item -> System.out.println(item));
//        THAT'S IT!!!!
    }

    /**
     * Same logic as example 7. Only difference is that we made our code neat and easy to read.
     * @param text
     * @return
     */
    private static Observable<List<String>> query(String text) {
        return Observable.create(subscriber -> createAndEmitList(subscriber, text));
    }

    private static void createAndEmitList(Subscriber<? super List<String>> subscriber, String text){
        List<String> myList = new ArrayList<String>();
        for(int i = 0 ; i < 100 ; i++){
            myList.add(text+ i + ".com");
        }
        if(!subscriber.isUnsubscribed()){
            subscriber.onNext(myList);
            subscriber.onCompleted();
        }
    }


}
