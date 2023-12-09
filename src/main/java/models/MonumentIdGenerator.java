package models;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MonumentIdGenerator {
	
	/*public static final String SEQUENCE_PREFIX = "sequence_prefix";
	
	private String sequencePrefix;
	
	private String sequenceCallSyntax;
	
	@Override
	public void configure(Type type,Properties params,ServiceRegistry serviceRegistry)
			throws MappingException {
	
		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(
		        JdbcEnvironment.class
		);
		
		final Dialect dialect = jdbcEnvironment.getDialect();
		
		final ConfigurationService configurationService = serviceRegistry.getService(
		        ConfigurationService.class
		);
		
		String globalEntityIdentifierPrefix = configurationService.getSetting(
		    "entity.identifier.prefix",
		    String.class,
		    "SEQ_"
		);
		
		sequencePrefix = ConfigurationHelper.getString(
		    SEQUENCE_PREFIX,
		    params,
		    globalEntityIdentifierPrefix
		);
		
		final String sequencePerEntitySuffix = ConfigurationHelper.getString(
		    SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,
		    params,
		    SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX
		);
		
		boolean preferSequencePerEntity = ConfigurationHelper.getBoolean(
		    SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,
		    params,
		    false
		);
		
		final String defaultSequenceName = preferSequencePerEntity
		        ? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
		        : SequenceStyleGenerator.DEF_SEQUENCE_NAME;
		
		sequenceCallSyntax = dialect.getSequenceNextValString(
		    ConfigurationHelper.getString(
		        SequenceStyleGenerator.SEQUENCE_PARAM,
		        params,
		        defaultSequenceName
		    )
		);
	}
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) {
	if (obj instanceof Identifiable) {
	    Identifiable identifiable = (Identifiable) obj;
	    Serializable id = identifiable.getId();
	
	    if (id != null) {
	        return id;
	    }
	}
	
	long seqValue = ((Number)
	        Session.class.cast(session)
	        .createNativeQuery(sequenceCallSyntax)
	        .uniqueResult()
	).longValue();
	
	return sequencePrefix + String.format("%011d%s", 0 ,seqValue);
	}*/
}