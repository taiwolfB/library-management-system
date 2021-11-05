package org.team4.libraryManagement.dao;

public class GeneralDAO<T>
{
    public Class<T> type;

    public GeneralDAO(Class<T> classT)
    {
        this.type=classT;
    }

}
