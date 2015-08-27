/**
 *
 */
package com.icms.hybris.sapintegragration.services;

import java.util.List;

import org.hibersap.session.SessionManager;



/**
 * @author nbeghin
 *
 */
public interface HibersapService
{

	public abstract void setRfcDestinationName(String rfcDestinationName);

	public abstract String getRfcDestinationName();

	public abstract SessionManager getSessionManager();

	public List<Class> getBapiClasses();

}