package org.xokyopo.filesharing.Domain.DepenceInjector;

import java.util.HashMap;
import java.util.Map;

public class DI {
    //TODO потом либо найду либо сам сконструирую данную приблуду
    private Map<? extends Class, Object> map = new HashMap<>();

    public <T, K extends T> T get(T impl) {
        //TODO как оно тут работатает хз даже
//        (T)this.map.get(T);
        return null;
    }

}
