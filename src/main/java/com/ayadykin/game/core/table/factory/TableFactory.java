package com.ayadykin.game.core.table.factory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.TableType;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Dependent
public class TableFactory {
    @Inject
    @Any
    private Instance<Table> table;

    public Table createTable(TableType.Type type) {
        TableLiteral literal = new TableLiteral(type);
        Instance<Table> typeМessages = table.select(literal);
        return typeМessages.get();
    }

    public class TableLiteral extends AnnotationLiteral<TableType> implements TableType {

        private Type type;

        public TableLiteral(Type type) {
            this.type = type;
        }

        public Type value() {
            return type;
        }
    }
}
