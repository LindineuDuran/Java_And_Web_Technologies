package br.com.lduran.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Allowance extends AbstractEntity
{
	@NotNull(message = "Allowance amount must be set")
	private BigDecimal allowanceAmount;

	@NotEmpty(message = "Allowance name must be set")
	private String allowanceName;

	public BigDecimal getAllowanceAmount()
	{
		return allowanceAmount;
	}

	public void setAllowanceAmount(BigDecimal allowanceAmount)
	{
		this.allowanceAmount = allowanceAmount;
	}

	public String getAllowanceName()
	{
		return allowanceName;
	}

	public void setAllowanceName(String allowanceName)
	{
		this.allowanceName = allowanceName;
	}
}
