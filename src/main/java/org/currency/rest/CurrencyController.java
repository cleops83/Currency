package org.currency.rest;

import org.currency.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.currency.dataobjects.CurrencyDO;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currencies")
    @ResponseBody
    public List<CurrencyDO> getAllCurrencies(){
        return currencyService.getAllCurrencies();


    }

    @GetMapping("/currencies/SYMBOL")
    @ResponseBody
    public List<CurrencyDO> getAllCurrencies(@RequestParam("id") String symbol){
        return currencyService.getAllCurrenciesBySymbol(symbol);


    }

}
