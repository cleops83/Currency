package org.currency.services;

import org.currency.dataobjects.CurrencyDO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    @Value(value = "classpath:eurofxref.csv")
    private Resource currenciesCSV;

    public List<CurrencyDO> getAllCurrencies(){
        String[] symbols = this.readFromCSV("Date");
        //TODO add regex date and validate if date changed
        String[] currencies = this.readFromCSV("18 May 2018");
        List<CurrencyDO> currencyDOS =  new ArrayList<>();

        if(symbols != null){
            List<String> symbolsList = Arrays.asList(symbols);
            List<String> currenciesList = Arrays.asList(currencies);
            for(int i=1;i<symbolsList.size();i++){
                CurrencyDO currencyDO = new CurrencyDO();
                if(symbolsList.get(i) !=null){
                    currencyDO.setSymbol(symbolsList.get(i).replaceAll("\\s+",""));
                }
                if(currenciesList.get(i) !=null){
                    currencyDO.setValue(currenciesList.get(i).replaceAll("\\s+",""));

                }
                currencyDOS.add(currencyDO);
            }


        }
        return currencyDOS;

    }


    private String[] readFromCSV(String lineStart) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            InputStream inputStream = currenciesCSV.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((line = br.readLine()) != null) {
                // use comma as separator
                if(line.startsWith(lineStart)) {
                    return line.split(cvsSplitBy);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    public List<CurrencyDO> getAllCurrenciesBySymbol(final String symbol) {
        List<CurrencyDO> currenciesBySymbol =  new ArrayList<>();
        List<CurrencyDO> currencies = this.getAllCurrencies();
        for(CurrencyDO currencyDO:currencies){
            if(currencyDO.getSymbol() != null && currencyDO.getSymbol().equals(symbol)){
                //System.out.println(currencyDO.getSymbol());
                currenciesBySymbol.add(currencyDO);
            }
        }

        return currenciesBySymbol;
    }
}
