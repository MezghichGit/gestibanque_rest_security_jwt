package com.sip.ams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sip.ams.entities.Article;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;


@Service
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository, ProviderRepository providerRepository) {
		this.articleRepository = articleRepository;
		this.providerRepository = providerRepository;
	}

	public List<Article> findAll() {
		return (List<Article>) articleRepository.findAll();
	}

	public Article create(Long providerId, Article article) {
		return providerRepository.findById(providerId).map(provider -> {
			article.setProvider(provider);
			return articleRepository.save(article);
		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + providerId + " not found"));
	}

}
