package com.sip.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sip.ams.entities.Article;
import com.sip.ams.services.ArticleService;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping({ "/articles" })
@CrossOrigin("*")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService;

	@GetMapping
	public List<Article> getAllArticles() {
		return (List<Article>) articleService.findAll();
	}

	@PostMapping("{providerId}")
	Article createArticle(@PathVariable(value = "providerId") Long providerId, @Valid @RequestBody Article article) {
		return articleService.create(providerId, article);
	}
	/*
	 * @PutMapping("/update/{providerId}/{articleId}") public Article
	 * updateArticle(@PathVariable (value = "providerId") Long providerId,
	 * 
	 * @PathVariable (value = "articleId") Long articleId,
	 * 
	 * @Valid @RequestBody Article articleRequest) {
	 * if(!providerRepository.existsById(providerId)) { throw new
	 * ResourceNotFoundException("ProviderId " + providerId + " not found"); }
	 * 
	 * return articleRepository.findById(articleId).map(article -> {
	 * article.setPrice(articleRequest.getPrice());
	 * article.setLabel(articleRequest.getLabel());
	 * article.setLabel(articleRequest.getPicture()); return
	 * articleRepository.save(article); }).orElseThrow(() -> new
	 * ResourceNotFoundException("ArticleId " + articleId + "not found")); }
	 * 
	 * @DeleteMapping("/delete/{articleId}") public ResponseEntity<?>
	 * deleteArticle(@PathVariable (value = "articleId") Long articleId) { return
	 * articleRepository.findById(articleId).map(article -> {
	 * articleRepository.delete(article); return ResponseEntity.ok().build();
	 * }).orElseThrow(() -> new
	 * ResourceNotFoundException("Article not found with id " + articleId)); }
	 */
}
