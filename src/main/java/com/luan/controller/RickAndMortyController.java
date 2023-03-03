package com.luan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.client.RickAndMortyClient;
import com.luan.response.CharacterResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webclient")
public class RickAndMortyController {
	
	RickAndMortyClient rickAndMortyClient;
	
	@GetMapping(value = "/character/{id}")
	public Mono<CharacterResponse> getCharacterById(@PathVariable(value = "id") String id) {
		return rickAndMortyClient.findCharacterById(id);
	}



	public RickAndMortyController(RickAndMortyClient rickAndMortyClient) {
	}
	
	
}
