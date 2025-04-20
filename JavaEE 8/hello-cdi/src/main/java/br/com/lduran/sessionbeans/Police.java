package br.com.lduran.sessionbeans;

import br.com.lduran.annotations.ServiceMan;
import br.com.lduran.interfaces.Salute;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.text.MessageFormat;

@Stateful
@ServiceMan(value = ServiceMan.ServiceType.POLICE)
@br.com.lduran.annotations.Police
public class Police implements Salute, Serializable
{
	@Override
	public String salute(String name)
	{
		return MessageFormat.format("Yes sir! {0}", name);
	}
}