package com.luan.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.luan.response.CharacterResponse;

@Service
@Slf4j
public class RickAndMortyClient {
	
	private final WebClient webClient;
	
	public RickAndMortyClient(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
	}

	public Mono<CharacterResponse> findCharacterById(String id) {
		log.info("Buscando personagem por id [{}]", id);
		return webClient
				.get()
				.uri("/character/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
				.bodyToMono(CharacterResponse.class)
				;
	}

}
