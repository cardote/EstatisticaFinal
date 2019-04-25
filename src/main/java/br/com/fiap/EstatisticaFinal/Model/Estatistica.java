package br.com.fiap.EstatisticaFinal.Model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
    private double sum;
    private double min;
    private double max;
    private double avg;
    private long count;
}
