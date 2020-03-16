package com.bcp.exchangerate.util.gce;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BusinessServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(BusinessServiceException.class);
    private static final String ALIAS_NOT_FOUND = "aliasnotfound";

    public BusinessServiceException(String alias) {
        this(alias, (Throwable)null, (String[])null);
    }

    public BusinessServiceException(String alias, String... messageParameters) {
        this(alias, (Throwable)null, messageParameters);
    }


    public BusinessServiceException(String alias, Throwable cause) {
        this(alias, cause, (String[])null);
    }

    public BusinessServiceException(String alias, Throwable cause, String... messageParameters) {
        super(cause);
    }
}
