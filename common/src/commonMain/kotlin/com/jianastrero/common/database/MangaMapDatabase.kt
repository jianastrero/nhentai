package com.jianastrero.common.database

import com.jianastrero.common.model.Manga

object MangaMapDatabase : IDatabase<Manga, String>{

    private val mangaMap: MutableMap<String, Manga> = mutableMapOf()

    override fun getAll(): List<Manga> = mangaMap.values.toList()

    override fun insert(item: Manga) {
        mangaMap[item.id] = item
    }

    override fun get(id: String): Manga? = mangaMap[id]

    override fun update(item: Manga) {
        mangaMap[item.id] = item
    }

    override fun delete(id: String) {
        mangaMap.remove(id)
    }

    override fun clear() {
        mangaMap.clear()
    }
}