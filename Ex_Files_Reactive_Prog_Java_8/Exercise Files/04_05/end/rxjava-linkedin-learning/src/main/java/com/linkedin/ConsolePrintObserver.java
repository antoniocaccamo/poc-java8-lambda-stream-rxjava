package com.linkedin;

import io.reactivex.observers.DefaultObserver;

public class ConsolePrintObserver extends DefaultObserver {
    @Override
    public void onNext(Object o) {
        System.out.print("[" + o + "]" + " - ");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("e.getMessage() = " + e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Observable Complete");
        System.out.println();
    }
}
