package org.xokyopo.hashtabel.hashset;

public interface HashSetInterface<HashedValue> {
    void put(HashedValue hashedValue);
    HashedValue get(HashedValue hashedValue);
    void del(HashedValue hashedValue);
    HashedValue[] values();
    int size();
    boolean isEmpty();
}
