package com.jianastrero.common.database

interface IDatabase<MODEL, ID> {

    fun getAll(): List<MODEL>

    fun insert(item: MODEL)

    fun get(id: ID): MODEL?

    fun update(item: MODEL)

    fun delete(id: ID)

    fun clear()
}