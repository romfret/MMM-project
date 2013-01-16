package locusta.project.entities.strategyDB;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class LocustaStrategy extends ImprovedNamingStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String classToTableName(String className) {
		return "locusta_" + className;
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return propertyName;
	}

	@Override
	public String tableName(String tableName) {
		return "locusta_" + tableName;
	}

	@Override
	public String columnName(String columnName) {
		return columnName;
	}

	public String propertyToTableName(String className, String propertyName) {
		return "locusta_" + classToTableName(className) + '_'
				+ propertyToColumnName(propertyName);
	}
}