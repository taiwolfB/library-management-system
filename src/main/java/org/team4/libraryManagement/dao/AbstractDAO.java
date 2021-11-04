package org.team4.libraryManagement.dao;

public class AbstractDAO<T>
{
    public Class<T> type;

    public AbstractDAO(Class<T> classT)
    {
        this.type=classT;
    }

}
