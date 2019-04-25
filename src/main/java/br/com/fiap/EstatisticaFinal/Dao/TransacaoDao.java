package br.com.fiap.EstatisticaFinal.Dao;

import br.com.fiap.EstatisticaFinal.Model.Estatistica;
import br.com.fiap.EstatisticaFinal.Model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoDao {

    private List<Transacao> transacoes = new ArrayList<>();

    public List<Transacao> list(){
        return transacoes;
    }

    public void save(Transacao transacao){
        transacoes.add(transacao);
    }
}
