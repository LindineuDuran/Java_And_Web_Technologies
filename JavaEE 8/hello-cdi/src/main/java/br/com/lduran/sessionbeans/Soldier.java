package br.com.lduran.sessionbeans;

import br.com.lduran.annotations.ServiceMan;
import br.com.lduran.interfaces.Salute;

import javax.ejb.Stateless;
import java.text.MessageFormat;

@Stateless
@ServiceMan(value = ServiceMan.ServiceType.SOLDIER)
@br.com.lduran.annotations.Soldier
public class Soldier implements Salute
{
	@Override
	public String salute(String name)
	{
		return MessageFormat.format("Aye Aye Capt {0}", name);
	}
}