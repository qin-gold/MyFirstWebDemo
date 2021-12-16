package com.wlf.core.domain.base.dto;

import com.wlf.core.annotation.Column;
import com.wlf.core.annotation.TablePk;
import com.wlf.core.enums.DbType;
import lombok.Data;

import java.util.Map;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/14 8:43
 */
@Data
public class TableColumn {
    private String Field;
    private String Type;
    private String Null;
    private String Key;
    private String oldField;
    private String Comment;
    public Column column;
    public TablePk pk;

    public TableColumn(Map<String, Object> map) {
        this.Field = (String) map.get("Field");
        this.Type = (String) map.get("Type");
        this.Null = (String) map.get("Null");
        this.Key = (String) map.get("Key");
        this.Comment = (String) map.get("Comment");
    }

    public TableColumn(Column column, TablePk pk) {
        this.column = column;
        this.pk = pk;
        this.Field = column.value();

        this.Type = column.type().getValue() + "(" + column.length() + ")";
        if (column.type() == DbType.Date || column.type() == DbType.Text || column.type() == DbType.DateTime) this.Type = column.type().getValue();
        if (column.type() == DbType.Int)  this.Type = column.type().getValue()+"(11)";
        this.Key = "";
        if (pk != null) this.Key = pk.isPk() ? "PRI" : "";

        this.Null = column.notNull() ? "NO" : "YES";
        if ("PRI".equals(this.Key)) this.Null = "NO";

        this.oldField = column.oldValue();
        this.Comment = column.remark();
    }

    public boolean equalsColumn(TableColumn column) {
        if (this.Field.equalsIgnoreCase(column.getField())) {
            if (this.Key.equalsIgnoreCase(column.getKey())) {
                if (this.Null.equalsIgnoreCase(column.getNull())) {
                    if (this.Type.equalsIgnoreCase(column.getType())) {
                        return this.Comment.equalsIgnoreCase(column.getComment().replaceAll(" ",""));
                    }
                }
            }
        }
        return false;
    }

    public boolean equalsColumnOne(TableColumn column) {
        return this.Field.equalsIgnoreCase(column.getField());
    }

}
