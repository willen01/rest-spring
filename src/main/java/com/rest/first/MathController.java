package com.rest.first;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.first.exceptions.UnsuportedMathOperationException;

@RestController // combinação entre @Controller e @ResponseBody
public class MathController {

    private final AtomicLong counter = new AtomicLong(); // moca um id;

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            // Parâmetro de variável
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("please, set a numeric value!");
        }

        return convertDouble(numberOne) + convertDouble(numberTwo);
    }

    private Double convertDouble(String strNumber) {
        if (strNumber == null)
            return 0D;

        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number))
            return Double.parseDouble(number);

        return 0D;
    }

    private boolean isNumeric(String value) {
        if (value == null)
            return false;
        String number = value.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
