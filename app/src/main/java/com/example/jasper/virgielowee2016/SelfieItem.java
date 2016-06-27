package com.example.jasper.virgielowee2016;

import java.io.Serializable;

/**
 * Created by Jasper on 27-6-2016.
 */
public class SelfieItem implements Serializable {

    private final int id;

    public SelfieItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
