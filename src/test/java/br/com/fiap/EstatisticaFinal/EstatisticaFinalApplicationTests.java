package br.com.fiap.EstatisticaFinal;

import br.com.fiap.EstatisticaFinal.Controller.EstatisticaController;
import br.com.fiap.EstatisticaFinal.Dao.TransacaoDao;
import br.com.fiap.EstatisticaFinal.Exception.TimeOutException;
import br.com.fiap.EstatisticaFinal.Model.Transacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EstatisticaController.class)
public class EstatisticaFinalApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransacaoDao daoTransacao;

	@Test
	public void saveTransactionCreated() throws Exception {
		daoTransacao = mock(TransacaoDao.class);

		Transacao transacao = new Transacao();
		transacao.setAmount(1000);
		transacao.setTimestamp(System.currentTimeMillis());

		doNothing().when(daoTransacao).save(transacao);

		mvc.perform(
				post("/transactions")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(transacao)))
				.andExpect(status().isCreated());
	}

	@Test
	public void saveTransactionNoContent() throws Exception {
		daoTransacao = mock(TransacaoDao.class);

		Transacao transacao = new Transacao();
		transacao.setAmount(100);
		transacao.setTimestamp(1556207632);

		doNothing().when(daoTransacao).save(transacao);

		mvc.perform(
				post("/transactions")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(transacao)))
				.andExpect(status().isNoContent());
	}
}
