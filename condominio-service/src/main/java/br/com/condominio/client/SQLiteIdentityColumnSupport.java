package br.com.condominio.client;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
	
    public SQLiteIdentityColumnSupport(Dialect dialect) {
		super(dialect);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean supportsIdentityColumns() {
        return true;
    }
 
    @Override
    public String getIdentitySelectString(String table, String column, int type) 
      throws MappingException {
        return "select nullif(max(" + column + "),0) + 1 from " + table;
    }
 
    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }

}
