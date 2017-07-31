package com.agit.brooks2.shared.object;

import java.io.Serializable;

/**
 *
 * @author bayutridewanto
 */
public interface AggregateObject<T> extends Serializable {

    boolean sameIdentityAs(T other);
}
