package desafio;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.conductor.desafio.DesafioApplication;
import br.com.conductor.desafio.model.Cliente;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DesafioApplication.class)
@AutoConfigureMockMvc
public class DesafioControllerTest {

	@Autowired
	private MockMvc mvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void salvar() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNome("TESTE JUNIT");
		cliente.setCpfcnpj("12345678900");
		cliente.setEmail("emailteste@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		byte[] novoCliente = mapper.writeValueAsBytes(cliente);
		mvc.perform(
				MockMvcRequestBuilders.post("/api/clientes")
						.content(novoCliente)
						.accept(MediaType.APPLICATION_JSON)).andExpect(
				status().is(201));
	}

	@Test
	public void atualizar() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setId(Long.valueOf(1));
		cliente.setNome("MARIA VALERIO EDITADO FRAGOSO");
		cliente.setCpfcnpj("7894900011517");
		cliente.setEmail("desafio7@gmail.com");
		mvc.perform(
				MockMvcRequestBuilders.post("/api/clientes")
						.content(converterObjetoParaByteJson(cliente))
						.accept(MediaType.APPLICATION_JSON)).andExpect(
				status().is(201));
		mvc.perform(MockMvcRequestBuilders.get("/api/clientes/1")
						.accept(MediaType.APPLICATION_JSON))						
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.nome").value("MARIA VALERIa EDITADO FRAGOSO"));
	}

	@Test
	public void consultaPorId() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/clientes/1").accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void paginar() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/clientes?page=1%size=5")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}
	
	private byte[] converterObjetoParaByteJson(Object objeto) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		byte[] novoCliente = mapper.writeValueAsBytes(objeto);
		return novoCliente;
	}

}
