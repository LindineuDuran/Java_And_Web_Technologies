package com.airhacks.ping.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 * @author airhacks.com
 */
@Path("ping")
public class PingResource
{
	@Inject
	ConfigService configService;

	@GET
	public String ping()
	{
		String message = configService.get("message");
		return message + " Jakarta EE lendo config.properties!";
	}
}
