package com.example.jasper.virgielowee2016;

import java.io.Serializable;

/**
 * Created by Jasper on 27-6-2016.
 */
public class SelfieItem implements Serializable {

    private final int id;
    private final int vindikvos;

    public SelfieItem(int id, int vindikvos) {
        this.id = id;
        this.vindikvos = vindikvos;
    }

    public int getId() {
        return id;
    }

    public int getVindikvos() { return vindikvos; }

}
