package com.valkcastellani.site.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Valk Castellani
 * @version 1.0
 * @date 2021-09-21
 */
public class CalcularIdade {

    private Integer anos = 0;
    private Integer meses = 0;
    private Integer dias = 0;
    private Date data;

    public CalcularIdade(Date data) {
        setData(data);
    }

    public final void setData(Date data) {
        this.data = data;
        calcularIdade();
    }

    public final Integer getAnos() {
        return anos;
    }

    public final Integer getMeses() {
        return meses;
    }

    public final Integer getDias() {
        return dias;
    }

    private void calcularIdade() {
        Calendar dataAtual;
        Calendar dataNascimento;

        dataAtual = Calendar.getInstance();
        dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(data);

        anos = anos + (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

        meses = meses + (dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH));

        dias = dias + (dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH));

        if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } 
        if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) >= dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                meses = 12 + meses;
                anos = anos - 1;
            } else if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } 
        if (dataAtual.get(Calendar.MONTH) > dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = meses - 1;
            }
        }
    }
}
