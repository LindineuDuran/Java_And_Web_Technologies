package br.com.lduran.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "tax_id"))
public class Tax extends AbstractEntity
{
    @Column(name = "TAX_RATE")
	private BigDecimal taxRate;

	public BigDecimal getTaxRate()
	{
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate)
	{
		this.taxRate = taxRate;
	}
}
