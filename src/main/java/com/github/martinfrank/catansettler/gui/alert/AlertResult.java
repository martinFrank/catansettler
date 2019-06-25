package com.github.martinfrank.catansettler.gui.alert;

public interface AlertResult <R> {

    boolean wasConfirmed();

    R getResult();
}
