package com.valkcastellani.site.controller;

import com.valkcastellani.site.rest.payload.MensagemDTO;
import com.valkcastellani.site.util.CalcularIdade;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Valk Castellani
 * @since 1.0
 * @date 2018-10-30
 * @version 1.0
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        LocalDate data = LocalDate.of(1975, Month.NOVEMBER, 5);
        CalcularIdade idade = new CalcularIdade(Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ModelAndView mv = new ModelAndView("index");

        mv.addObject("anos", idade.getAnos());
        mv.addObject("meses", idade.getMeses());
        mv.addObject("dias", idade.getDias());
        mv.addObject("mensagem", new MensagemDTO());

        return mv;
    }
}
