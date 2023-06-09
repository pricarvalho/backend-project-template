package com.company.app.common;

import java.util.Objects;

import org.jooq.Record;

public interface JooqRepository<T extends Record> {
    
    // Sets the column change to false when the value didn't change, avoiding updating all columns unecessary. 
    // Feature suppose to be introduce on JOOQ-3.19V (https://github.com/jOOQ/jOOQ/issues/5394)
    default T optimizeColumnsUpdateOf(T record) {
            for (int i = 0; i < record.size(); i++)
                if (Objects.equals(record.get(i), record.original(i)))
                    record.changed(i, false);
        
       return record;
    }

}
