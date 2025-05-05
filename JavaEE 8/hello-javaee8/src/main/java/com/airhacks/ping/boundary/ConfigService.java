package com.airhacks.ping.boundary;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class ConfigService
{
	private final Properties properties = new Properties();

	public ConfigService()
	{
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties"))
		{
			if (input == null)
			{
				throw new RuntimeException("Arquivo config.properties não encontrado.");
			}

			properties.load(input);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Erro ao carregar config.properties", e);
		}
	}

	public String get(String key)
	{
		return properties.getProperty(key);
	}
}