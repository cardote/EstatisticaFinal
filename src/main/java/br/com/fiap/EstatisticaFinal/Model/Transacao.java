package br.com.fiap.EstatisticaFinal.Model;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @NotNull
    @DecimalMin("0.1")
    private double amount ;
    @NotNull
    @DecimalMin("1")
    private long timestamp;
}
