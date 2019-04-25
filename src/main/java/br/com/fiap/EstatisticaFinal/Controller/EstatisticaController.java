package br.com.fiap.EstatisticaFinal.Controller;

import br.com.fiap.EstatisticaFinal.Dao.TransacaoDao;
import br.com.fiap.EstatisticaFinal.Exception.TimeOutException;
import br.com.fiap.EstatisticaFinal.Model.Estatistica;
import br.com.fiap.EstatisticaFinal.Model.Transacao;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Api(value = "Estatistica")
public class EstatisticaController {

    private TransacaoDao daoTransacao;
    private Estatistica estatistica;

    public EstatisticaController() {
        daoTransacao = new TransacaoDao();
        estatistica = new Estatistica();
    }

    @PostMapping("/transactions")
    public ResponseEntity save(@RequestBody @Valid Transacao transacao){
        Timestamp localTime = new Timestamp(System.currentTimeMillis());

        if(transacao.getTimestamp() + 60000 >= localTime.getTime()){
            daoTransacao.save(transacao);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new TimeOutException();
        }

    }
    @GetMapping("/statistics")
    public ResponseEntity<Estatistica> get(){
        List<Transacao> list = daoTransacao.list();

        if(list.size() == 0){
            return new ResponseEntity(estatistica, HttpStatus.NOT_FOUND);
        }

        double sum = 0;
        double avg = 0;
        double max = 0;
        double min = list.get(0).getAmount();

        for (Transacao transacao : list){
            sum += transacao.getAmount();

            if(transacao.getAmount() > max){
                max = transacao.getAmount();
            }

            if(transacao.getAmount() < min) {
                min = transacao.getAmount();
            }
        }
        avg = sum / list.size();

        estatistica.setSum(sum);
        estatistica.setAvg(avg);
        estatistica.setMax(max);
        estatistica.setMin(min);
        estatistica.setCount(list.size());

        return new ResponseEntity(estatistica, HttpStatus.OK);
    }
}
