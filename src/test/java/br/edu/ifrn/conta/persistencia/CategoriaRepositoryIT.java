/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.edu.ifrn.conta.persistencia;

import jakarta.inject.Inject;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.dominio.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CategoriaRepositoryIT {

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private CategoriaFabrica categoriaFabrica;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.categoriaRepository)
			.isNotNull();
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		Categoria categoria = this.categoriaFabrica.categoria("CATEGORIA UNICA");

		// executa a operacao a ser testada
		this.categoriaRepository.delete(categoria);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaRepository.findById(categoria.getId()))
			.isNull();
	}

	@Test
	public void salvarUm() {
		// executa a operacao a ser testada
		Categoria categoria = this.categoriaFabrica.transporte();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(categoria.getId())
			.isNotNull();
	}

}
