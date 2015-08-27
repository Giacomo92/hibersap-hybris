/**
 *
 */
package com.icms.hybris.sapintegragration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.execution.jco.JCoContext;
import org.hibersap.execution.jco.JCoEnvironment;
import org.hibersap.session.SessionManager;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.icms.hybris.sapintegragration.services.HibersapService;


/**
 * @author nbeghin
 *
 */
@Service
public class DefaultHibersapService implements HibersapService
{
	private String rfcDestinationName;
	private final Logger LOG = Logger.getLogger(DefaultHibersapService.class);
	private final List<Class> bapiClasses = new ArrayList<Class>();

	public DefaultHibersapService(final String rfcDestinationName)
	{
		super();
		this.rfcDestinationName = rfcDestinationName;
		LOG.info("Initializing target RFC destination " + rfcDestinationName);
	}

	public DefaultHibersapService()
	{
		super();
		LOG.info("Initializing without target RFC destination");
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see com.icms.hybris.sapintegragration.services.HibersapService#getSessionManager()
	 */
	@Override
	public SessionManager getSessionManager()
	{
		JCoEnvironment.RFC_DESTINATION_NAME = rfcDestinationName;
		final SessionManagerConfig cfg = new SessionManagerConfig(rfcDestinationName).setContext(JCoContext.class.getName());
		for (final Class annotatedClass : bapiClasses)
		{
			cfg.addAnnotatedClass(annotatedClass);
		}
		final AnnotationConfiguration configuration = new AnnotationConfiguration(cfg);
		return configuration.buildSessionManager();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.icms.hybris.sapintegragration.services.HibersapService#setRfcDestinationName(java.lang.String)
	 */
	@Override
	@Required
	public void setRfcDestinationName(final String rfcDestinationName)
	{
		LOG.info("Setting target RFC destination " + rfcDestinationName);
		this.rfcDestinationName = rfcDestinationName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.icms.hybris.sapintegragration.services.HibersapService#getRfcDestinationName()
	 */
	@Override
	public String getRfcDestinationName()
	{
		return rfcDestinationName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.icms.hybris.sapintegragration.services.HibersapService#getBapiClasses()
	 */
	@Override
	public List<Class> getBapiClasses()
	{
		return bapiClasses;
	}

}
