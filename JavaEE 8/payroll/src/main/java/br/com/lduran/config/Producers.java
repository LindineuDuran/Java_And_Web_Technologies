package br.com.lduran.config;

import jakarta.enterprise.inject.Produces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class Producers
{
	@Produces
	@PersistenceContext
	EntityManager entityManager;
}
