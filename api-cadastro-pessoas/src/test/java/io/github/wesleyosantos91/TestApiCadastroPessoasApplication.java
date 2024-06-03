package io.github.wesleyosantos91;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestApiCadastroPessoasApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiCadastroPessoasApplication::main).with(TestApiCadastroPessoasApplication.class).run(args);
	}

}
