/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
/**
/**
 *
 * @author Asus
 */
public class SetTabel extends AbstractTableModel {
    
    private final ResultSet resultSet;
    
    public SetTabel(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public int getRowCount() {
        try{
            if(resultSet == null){
                return 0;
            }else{
                resultSet.last();
                return resultSet.getRow();
            }
        }catch(SQLException e){
            System.out.println("resultset generating error"
                    + " while getting rows count");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try{
            if(resultSet == null){
                return 0;
            }else{
                return resultSet.getMetaData().getColumnCount();
            }
        }catch(SQLException e){
            System.out.println("resultset generating error "
                    + "while getting column count");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        if(rowIndex < 0 || rowIndex > getRowCount() 
                || columnIndex < 0 || columnIndex > getColumnCount()){
            return null;
        }
        
        try{
            if(resultSet == null){
                return null;
            }else{
                resultSet.absolute(rowIndex + 1);
                return resultSet.getObject(columnIndex + 1);
            }
        }catch(SQLException e){
            System.out.println("resultset generating error while fetching rows");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    @Override
    public String getColumnName(int columnIndex){
        try{
            return resultSet.getMetaData().getColumnName(columnIndex + 1);
        }catch(SQLException e){
            System.out.println("resultset generating "
                    + "error while fetching column name");
            System.out.println(e.getMessage());
        }
        
        return super.getColumnName(columnIndex);
    }
}
