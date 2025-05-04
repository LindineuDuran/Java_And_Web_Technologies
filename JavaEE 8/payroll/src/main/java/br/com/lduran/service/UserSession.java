package br.com.lduran.service;

import jakarta.ejb.PostActivate;
import jakarta.ejb.PrePassivate;
import jakarta.ejb.Stateful;

import java.io.Serializable;

@Stateful
public class UserSession implements Serializable
{
	public String getCurrentUserName()
	{
		return "";
	}

	@PrePassivate
	private void passivate()
	{

	}

	@PostActivate
	private void active()
	{

	}
}