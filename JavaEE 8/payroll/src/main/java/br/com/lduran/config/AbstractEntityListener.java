package br.com.lduran.config;

import br.com.lduran.entities.AbstractEntity;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AbstractEntityListener
{
	@PrePersist
	public void setCreatedOn(AbstractEntity abstractEntity)
	{
		abstractEntity.setCreatedOn(LocalDateTime.now());
	}

	@PreUpdate
	public void setUpdatedOn(AbstractEntity abstractEntity)
	{
		abstractEntity.setUpdatedOn(LocalDateTime.now());
	}
}