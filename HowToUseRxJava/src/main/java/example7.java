import rx.Observable;

import java.util.List;

/**
 *
 * Create two Observables
 * Merge them
 * Reduce the two results into one
 * Map the result to the format you want
 * Print it to screen using method subscribe()
 *
 * Created by matie on 28/04/15.
 */
public class example7 {

    private static Integer[] firstList = {1,3,5};
    private static Integer[] secondList = {2,4,6};

    private static Observable<Integer> firstObservable = Observable.from(firstList);
    private static Observable<Integer> secondObservable = Observable.from(secondList);

    public static void main(String[] args){

        //reduce() will sum first item with the second, combined with the third, and so on
        Observable.merge(firstObservable, secondObservable)
                .reduce((first, second) -> first + second)
                .map(num -> "Sum of items: " + num)
                .subscribe(message -> System.out.println(message));

        //reduce() with an initialValue will sum [initial + (first) + (second) + (third)...]
        int initialValue = 10;
        Observable.merge(firstObservable, secondObservable)
                .reduce(initialValue, (first, second) -> first + second)
                .map(num -> "Sum with initial value of 10: " + num)
                .subscribe(message -> System.out.println(message));

        //reduce() with an initialValue will sum [initial + (initial+first) + (initial+second) + (initial+third)...]
        Observable.merge(firstObservable, secondObservable)
                .reduce(initialValue, (first, second) -> initialValue + first + second)
                .map(num -> "Sum with initial value of 10 in function: " + num)
                .subscribe(message -> System.out.println(message));

        //observe how the two observables become one
        Observable.merge(firstObservable, secondObservable)
                .map(num -> "Single item: " + num)
                .subscribe(message -> System.out.println(message));
    }
}
