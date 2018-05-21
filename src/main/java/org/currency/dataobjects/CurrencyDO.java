package org.currency.dataobjects;

import java.io.Serializable;

public class CurrencyDO implements Serializable {
    private String value;
    private String symbol;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
